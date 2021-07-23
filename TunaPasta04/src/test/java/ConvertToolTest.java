import com.tunaPasta04.tool.ConvertTool;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author TunaSashimi
 * @date 2021/7/14 14:11
 * @Copyright 2021 TunaSashimi. All rights reserved.
 * @Description
 */
public class ConvertToolTest {
    @Test
    public void getBinary() {
        assertEquals("1", ConvertTool.getBinary("1"));
        assertEquals("10", ConvertTool.getBinary("2"));
        assertEquals("ERROR", ConvertTool.getBinary("abc"));
    }

    @Test
    public void getOctal() {
        assertEquals("10", ConvertTool.getOctal("8"));
        assertEquals("11", ConvertTool.getOctal("9"));
        assertEquals("ERROR", ConvertTool.getOctal("abc"));
    }

    @Test
    public void getHex() {
        assertEquals("10", ConvertTool.getHex("16"));
        assertEquals("11", ConvertTool.getHex("17"));
        assertEquals("ERROR", ConvertTool.getHex("abc"));
    }
}
