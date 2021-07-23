//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tunaPasta18.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public class Base64HV {
    private Base64HV() {
    }

    public static Base64HV.Encoder getEncoder() {
        return Base64HV.Encoder.RFC4648;
    }

    public static Base64HV.Encoder getUrlEncoder() {
        return Base64HV.Encoder.RFC4648_URLSAFE;
    }

    public static Base64HV.Encoder getMimeEncoder() {
        return Base64HV.Encoder.RFC2045;
    }

    public static Base64HV.Encoder getMimeEncoder(int var0, byte[] var1) {
        Objects.requireNonNull(var1);
        int[] var2 = Base64HV.Decoder.fromBase64;
        byte[] var3 = var1;
        int var4 = var1.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            byte var6 = var3[var5];
            if (var2[var6 & 255] != -1) {
                throw new IllegalArgumentException("Illegal base64 line separator character 0x" + Integer.toString(var6, 16));
            }
        }

        if (var0 <= 0) {
            return Base64HV.Encoder.RFC4648;
        } else {
            return new Base64HV.Encoder(false, var1, var0 >> 2 << 2, true);
        }
    }

    public static Base64HV.Decoder getDecoder() {
        return Base64HV.Decoder.RFC4648;
    }

    public static Base64HV.Decoder getUrlDecoder() {
        return Base64HV.Decoder.RFC4648_URLSAFE;
    }

    public static Base64HV.Decoder getMimeDecoder() {
        return Base64HV.Decoder.RFC2045;
    }

    private static class DecInputStream extends InputStream {
        private final InputStream is;
        private final boolean isMIME;
        private final int[] base64;
        private int bits = 0;
        private int nextin = 18;
        private int nextout = -8;
        private boolean eof = false;
        private boolean closed = false;
        private byte[] sbBuf = new byte[1];

        DecInputStream(InputStream var1, int[] var2, boolean var3) {
            this.is = var1;
            this.base64 = var2;
            this.isMIME = var3;
        }

        public int read() throws IOException {
            return this.read(this.sbBuf, 0, 1) == -1 ? -1 : this.sbBuf[0] & 255;
        }

        public int read(byte[] var1, int var2, int var3) throws IOException {
            if (this.closed) {
                throw new IOException("Stream is closed");
            } else if (this.eof && this.nextout < 0) {
                return -1;
            } else if (var2 >= 0 && var3 >= 0 && var3 <= var1.length - var2) {
                int var4 = var2;
                if (this.nextout >= 0) {
                    do {
                        if (var3 == 0) {
                            return var2 - var4;
                        }

                        var1[var2++] = (byte)(this.bits >> this.nextout);
                        --var3;
                        this.nextout -= 8;
                    } while(this.nextout >= 0);

                    this.bits = 0;
                }

                while(true) {
                    if (var3 > 0) {
                        int var5 = this.is.read();
                        if (var5 == -1) {
                            this.eof = true;
                            if (this.nextin != 18) {
                                if (this.nextin == 12) {
                                    throw new IOException("Base64 stream has one un-decoded dangling byte.");
                                }

                                var1[var2++] = (byte)(this.bits >> 16);
                                --var3;
                                if (this.nextin == 0) {
                                    if (var3 == 0) {
                                        this.bits >>= 8;
                                        this.nextout = 0;
                                    } else {
                                        var1[var2++] = (byte)(this.bits >> 8);
                                    }
                                }
                            }

                            if (var2 == var4) {
                                return -1;
                            }

                            return var2 - var4;
                        }

                        if (var5 != 61) {
                            if ((var5 = this.base64[var5]) == -1) {
                                if (!this.isMIME) {
                                    throw new IOException("Illegal base64 character " + Integer.toString(var5, 16));
                                }
                                continue;
                            }

                            this.bits |= var5 << this.nextin;
                            if (this.nextin != 0) {
                                this.nextin -= 6;
                                continue;
                            }

                            this.nextin = 18;
                            this.nextout = 16;

                            while(this.nextout >= 0) {
                                var1[var2++] = (byte)(this.bits >> this.nextout);
                                --var3;
                                this.nextout -= 8;
                                if (var3 == 0 && this.nextout >= 0) {
                                    return var2 - var4;
                                }
                            }

                            this.bits = 0;
                            continue;
                        }

                        if (this.nextin == 18 || this.nextin == 12 || this.nextin == 6 && this.is.read() != 61) {
                            throw new IOException("Illegal base64 ending sequence:" + this.nextin);
                        }

                        var1[var2++] = (byte)(this.bits >> 16);
                        --var3;
                        if (this.nextin == 0) {
                            if (var3 == 0) {
                                this.bits >>= 8;
                                this.nextout = 0;
                            } else {
                                var1[var2++] = (byte)(this.bits >> 8);
                            }
                        }

                        this.eof = true;
                    }

                    return var2 - var4;
                }
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        public int available() throws IOException {
            if (this.closed) {
                throw new IOException("Stream is closed");
            } else {
                return this.is.available();
            }
        }

        public void close() throws IOException {
            if (!this.closed) {
                this.closed = true;
                this.is.close();
            }

        }
    }

    private static class EncOutputStream extends FilterOutputStream {
        private int leftover = 0;
        private int b0;
        private int b1;
        private int b2;
        private boolean closed = false;
        private final char[] base64;
        private final byte[] newline;
        private final int linemax;
        private final boolean doPadding;
        private int linepos = 0;

        EncOutputStream(OutputStream var1, char[] var2, byte[] var3, int var4, boolean var5) {
            super(var1);
            this.base64 = var2;
            this.newline = var3;
            this.linemax = var4;
            this.doPadding = var5;
        }

        public void write(int var1) throws IOException {
            byte[] var2 = new byte[]{(byte)(var1 & 255)};
            this.write(var2, 0, 1);
        }

        private void checkNewline() throws IOException {
            if (this.linepos == this.linemax) {
                this.out.write(this.newline);
                this.linepos = 0;
            }

        }

        public void write(byte[] var1, int var2, int var3) throws IOException {
            if (this.closed) {
                throw new IOException("Stream is closed");
            } else if (var2 >= 0 && var3 >= 0 && var3 <= var1.length - var2) {
                if (var3 != 0) {
                    if (this.leftover != 0) {
                        if (this.leftover == 1) {
                            this.b1 = var1[var2++] & 255;
                            --var3;
                            if (var3 == 0) {
                                ++this.leftover;
                                return;
                            }
                        }

                        this.b2 = var1[var2++] & 255;
                        --var3;
                        this.checkNewline();
                        this.out.write(this.base64[this.b0 >> 2]);
                        this.out.write(this.base64[this.b0 << 4 & 63 | this.b1 >> 4]);
                        this.out.write(this.base64[this.b1 << 2 & 63 | this.b2 >> 6]);
                        this.out.write(this.base64[this.b2 & 63]);
                        this.linepos += 4;
                    }

                    int var4 = var3 / 3;

                    for(this.leftover = var3 - var4 * 3; var4-- > 0; this.linepos += 4) {
                        this.checkNewline();
                        int var5 = (var1[var2++] & 255) << 16 | (var1[var2++] & 255) << 8 | var1[var2++] & 255;
                        this.out.write(this.base64[var5 >>> 18 & 63]);
                        this.out.write(this.base64[var5 >>> 12 & 63]);
                        this.out.write(this.base64[var5 >>> 6 & 63]);
                        this.out.write(this.base64[var5 & 63]);
                    }

                    if (this.leftover == 1) {
                        this.b0 = var1[var2++] & 255;
                    } else if (this.leftover == 2) {
                        this.b0 = var1[var2++] & 255;
                        this.b1 = var1[var2++] & 255;
                    }

                }
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }

        public void close() throws IOException {
            if (!this.closed) {
                this.closed = true;
                if (this.leftover == 1) {
                    this.checkNewline();
                    this.out.write(this.base64[this.b0 >> 2]);
                    this.out.write(this.base64[this.b0 << 4 & 63]);
                    if (this.doPadding) {
                        this.out.write(61);
                        this.out.write(61);
                    }
                } else if (this.leftover == 2) {
                    this.checkNewline();
                    this.out.write(this.base64[this.b0 >> 2]);
                    this.out.write(this.base64[this.b0 << 4 & 63 | this.b1 >> 4]);
                    this.out.write(this.base64[this.b1 << 2 & 63]);
                    if (this.doPadding) {
                        this.out.write(61);
                    }
                }

                this.leftover = 0;
                this.out.close();
            }

        }
    }

    public static class Decoder {
        private final boolean isURL;
        private final boolean isMIME;
        private static final int[] fromBase64 = new int[256];
        private static final int[] fromBase64URL;
        static final Base64HV.Decoder RFC4648;
        static final Base64HV.Decoder RFC4648_URLSAFE;
        static final Base64HV.Decoder RFC2045;

        private Decoder(boolean var1, boolean var2) {
            this.isURL = var1;
            this.isMIME = var2;
        }

        public byte[] decode(byte[] var1) {
            byte[] var2 = new byte[this.outLength(var1, 0, var1.length)];
            int var3 = this.decode0(var1, 0, var1.length, var2);
            if (var3 != var2.length) {
                var2 = Arrays.copyOf(var2, var3);
            }

            return var2;
        }

        public byte[] decode(String var1) {
            return this.decode(var1.getBytes(StandardCharsets.ISO_8859_1));
        }

        public int decode(byte[] var1, byte[] var2) {
            int var3 = this.outLength(var1, 0, var1.length);
            if (var2.length < var3) {
                throw new IllegalArgumentException("Output byte array is too small for decoding all input bytes");
            } else {
                return this.decode0(var1, 0, var1.length, var2);
            }
        }

        public ByteBuffer decode(ByteBuffer var1) {
            int var2 = var1.position();

            try {
                byte[] var3;
                int var4;
                int var5;
                if (var1.hasArray()) {
                    var3 = var1.array();
                    var4 = var1.arrayOffset() + var1.position();
                    var5 = var1.arrayOffset() + var1.limit();
                    var1.position(var1.limit());
                } else {
                    var3 = new byte[var1.remaining()];
                    var1.get(var3);
                    var4 = 0;
                    var5 = var3.length;
                }

                byte[] var6 = new byte[this.outLength(var3, var4, var5)];
                return ByteBuffer.wrap(var6, 0, this.decode0(var3, var4, var5, var6));
            } catch (IllegalArgumentException var7) {
                var1.position(var2);
                throw var7;
            }
        }

        public InputStream wrap(InputStream var1) {
            Objects.requireNonNull(var1);
            return new Base64HV.DecInputStream(var1, this.isURL ? fromBase64URL : fromBase64, this.isMIME);
        }

        private int outLength(byte[] var1, int var2, int var3) {
            int[] var4 = this.isURL ? fromBase64URL : fromBase64;
            int var5 = 0;
            int var6 = var3 - var2;
            if (var6 == 0) {
                return 0;
            } else if (var6 < 2) {
                if (this.isMIME && var4[0] == -1) {
                    return 0;
                } else {
                    throw new IllegalArgumentException("Input byte[] should at least have 2 bytes for base64 bytes");
                }
            } else {
                if (this.isMIME) {
                    int var7 = 0;

                    while(var2 < var3) {
                        int var8 = var1[var2++] & 255;
                        if (var8 == 61) {
                            var6 -= var3 - var2 + 1;
                            break;
                        }

                        if (var4[var8] == -1) {
                            ++var7;
                        }
                    }

                    var6 -= var7;
                } else if (var1[var3 - 1] == 61) {
                    ++var5;
                    if (var1[var3 - 2] == 61) {
                        ++var5;
                    }
                }

                if (var5 == 0 && (var6 & 3) != 0) {
                    var5 = 4 - (var6 & 3);
                }

                return 3 * ((var6 + 3) / 4) - var5;
            }
        }

        private int decode0(byte[] var1, int var2, int var3, byte[] var4) {
            int[] var5 = this.isURL ? fromBase64URL : fromBase64;
            int var6 = 0;
            int var7 = 0;
            int var8 = 18;

            while(var2 < var3) {
                int var9 = var1[var2++] & 255;
                if ((var9 = var5[var9]) < 0) {
                    if (var9 == -2) {
                        if ((var8 != 6 || var2 != var3 && var1[var2++] == 61) && var8 != 18) {
                            break;
                        }

                        throw new IllegalArgumentException("Input byte array has wrong 4-byte ending unit");
                    }

                    if (!this.isMIME) {
                        throw new IllegalArgumentException("Illegal base64 character " + Integer.toString(var1[var2 - 1], 16));
                    }
                } else {
                    var7 |= var9 << var8;
                    var8 -= 6;
                    if (var8 < 0) {
                        var4[var6++] = (byte)(var7 >> 16);
                        var4[var6++] = (byte)(var7 >> 8);
                        var4[var6++] = (byte)var7;
                        var8 = 18;
                        var7 = 0;
                    }
                }
            }

            if (var8 == 6) {
                var4[var6++] = (byte)(var7 >> 16);
            } else if (var8 == 0) {
                var4[var6++] = (byte)(var7 >> 16);
                var4[var6++] = (byte)(var7 >> 8);
            } else if (var8 == 12) {
                throw new IllegalArgumentException("Last unit does not have enough valid bits");
            }

            do {
                if (var2 >= var3) {
                    return var6;
                }
            } while(this.isMIME && var5[var1[var2++]] < 0);

            throw new IllegalArgumentException("Input byte array has incorrect ending byte at " + var2);
        }

        static {
            Arrays.fill(fromBase64, -1);

            int var0;
            for(var0 = 0; var0 < Base64HV.Encoder.toBase64.length; fromBase64[Base64HV.Encoder.toBase64[var0]] = var0++) {
                ;
            }

            fromBase64[61] = -2;
            fromBase64URL = new int[256];
            Arrays.fill(fromBase64URL, -1);

            for(var0 = 0; var0 < Base64HV.Encoder.toBase64URL.length; fromBase64URL[Base64HV.Encoder.toBase64URL[var0]] = var0++) {
                ;
            }

            fromBase64URL[61] = -2;
            RFC4648 = new Base64HV.Decoder(false, false);
            RFC4648_URLSAFE = new Base64HV.Decoder(true, false);
            RFC2045 = new Base64HV.Decoder(false, true);
        }
    }

    public static class Encoder {
        private final byte[] newline;
        private final int linemax;
        private final boolean isURL;
        private final boolean doPadding;
        private static final char[] toBase64 = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        private static final char[] toBase64URL = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'};
        private static final int MIMELINEMAX = 76;
        private static final byte[] CRLF = new byte[]{13, 10};
        static final Base64HV.Encoder RFC4648 = new Base64HV.Encoder(false, (byte[])null, -1, true);
        static final Base64HV.Encoder RFC4648_URLSAFE = new Base64HV.Encoder(true, (byte[])null, -1, true);
        static final Base64HV.Encoder RFC2045;

        private Encoder(boolean var1, byte[] var2, int var3, boolean var4) {
            this.isURL = var1;
            this.newline = var2;
            this.linemax = var3;
            this.doPadding = var4;
        }

        private final int outLength(int var1) {
            boolean var2 = false;
            int var4;
            if (this.doPadding) {
                var4 = 4 * ((var1 + 2) / 3);
            } else {
                int var3 = var1 % 3;
                var4 = 4 * (var1 / 3) + (var3 == 0 ? 0 : var3 + 1);
            }

            if (this.linemax > 0) {
                var4 += (var4 - 1) / this.linemax * this.newline.length;
            }

            return var4;
        }

        public byte[] encode(byte[] var1) {
            int var2 = this.outLength(var1.length);
            byte[] var3 = new byte[var2];
            int var4 = this.encode0(var1, 0, var1.length, var3);
            return var4 != var3.length ? Arrays.copyOf(var3, var4) : var3;
        }

        public int encode(byte[] var1, byte[] var2) {
            int var3 = this.outLength(var1.length);
            if (var2.length < var3) {
                throw new IllegalArgumentException("Output byte array is too small for encoding all input bytes");
            } else {
                return this.encode0(var1, 0, var1.length, var2);
            }
        }

        public String encodeToString(byte[] var1) {
            byte[] var2 = this.encode(var1);
            return new String(var2, 0, 0, var2.length);
        }

        public ByteBuffer encode(ByteBuffer var1) {
            int var2 = this.outLength(var1.remaining());
            byte[] var3 = new byte[var2];
            boolean var4 = false;
            int var6;
            if (var1.hasArray()) {
                var6 = this.encode0(var1.array(), var1.arrayOffset() + var1.position(), var1.arrayOffset() + var1.limit(), var3);
                var1.position(var1.limit());
            } else {
                byte[] var5 = new byte[var1.remaining()];
                var1.get(var5);
                var6 = this.encode0(var5, 0, var5.length, var3);
            }

            if (var6 != var3.length) {
                var3 = Arrays.copyOf(var3, var6);
            }

            return ByteBuffer.wrap(var3);
        }

        public OutputStream wrap(OutputStream var1) {
            Objects.requireNonNull(var1);
            return new Base64HV.EncOutputStream(var1, this.isURL ? toBase64URL : toBase64, this.newline, this.linemax, this.doPadding);
        }

        public Base64HV.Encoder withoutPadding() {
            return !this.doPadding ? this : new Base64HV.Encoder(this.isURL, this.newline, this.linemax, false);
        }

        private int encode0(byte[] var1, int var2, int var3, byte[] var4) {
            char[] var5 = this.isURL ? toBase64URL : toBase64;
            int var6 = var2;
            int var7 = (var3 - var2) / 3 * 3;
            int var8 = var2 + var7;
            if (this.linemax > 0 && var7 > this.linemax / 4 * 3) {
                var7 = this.linemax / 4 * 3;
            }

            int var9 = 0;

            while(true) {
                int var10;
                int var11;
                int var13;
                do {
                    do {
                        if (var6 >= var8) {
                            if (var6 < var3) {
                                var10 = var1[var6++] & 255;
                                var4[var9++] = (byte)var5[var10 >> 2];
                                if (var6 == var3) {
                                    var4[var9++] = (byte)var5[var10 << 4 & 63];
                                    if (this.doPadding) {
                                        var4[var9++] = 61;
                                        var4[var9++] = 61;
                                    }
                                } else {
                                    var11 = var1[var6++] & 255;
                                    var4[var9++] = (byte)var5[var10 << 4 & 63 | var11 >> 4];
                                    var4[var9++] = (byte)var5[var11 << 2 & 63];
                                    if (this.doPadding) {
                                        var4[var9++] = 61;
                                    }
                                }
                            }

                            return var9;
                        }

                        var10 = Math.min(var6 + var7, var8);
                        var11 = var6;

                        for(int var12 = var9; var11 < var10; var4[var12++] = (byte)var5[var13 & 63]) {
                            var13 = (var1[var11++] & 255) << 16 | (var1[var11++] & 255) << 8 | var1[var11++] & 255;
                            var4[var12++] = (byte)var5[var13 >>> 18 & 63];
                            var4[var12++] = (byte)var5[var13 >>> 12 & 63];
                            var4[var12++] = (byte)var5[var13 >>> 6 & 63];
                        }

                        var11 = (var10 - var6) / 3 * 4;
                        var9 += var11;
                        var6 = var10;
                    } while(var11 != this.linemax);
                } while(var10 >= var3);

                byte[] var16 = this.newline;
                var13 = var16.length;

                for(int var14 = 0; var14 < var13; ++var14) {
                    byte var15 = var16[var14];
                    var4[var9++] = var15;
                }
            }
        }

        static {
            RFC2045 = new Base64HV.Encoder(false, CRLF, 76, true);
        }
    }
}
