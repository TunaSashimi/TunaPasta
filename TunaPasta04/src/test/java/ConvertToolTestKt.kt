import com.tunaPasta04.tool.ConvertTool
import org.junit.Assert
import org.junit.Test

/**
 * @author TunaSashimi
 * @date 2021/7/14 16:45
 * @Copyright 2021 TunaSashimi. All rights reserved.
 * @Description
 */
class ConvertToolTestKt {
    @Test
    fun getBinaryTest() {
        Assert.assertEquals("1", ConvertTool.getBinary("1"))
        Assert.assertEquals("10", ConvertTool.getBinary("2"))
        Assert.assertEquals("ERROR", ConvertTool.getBinary("abc"))
    }

    @Test
    fun getOctaltest() {
        Assert.assertEquals("10", ConvertTool.getOctal("8"));
        Assert.assertEquals("11", ConvertTool.getOctal("9"));
        Assert.assertEquals("ERROR", ConvertTool.getOctal("abc"));
    }

    @Test
    fun getHexTest() {
        Assert.assertEquals("10", ConvertTool.getHex("8"));
        Assert.assertEquals("11", ConvertTool.getHex("9"));
        Assert.assertEquals("ERROR", ConvertTool.getHex("abc"));
    }
}