package task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.math.BigInteger

internal class UnsignedBigIntegerTest {

    @Test
    @Tag("8")
    fun plus() {
        assertEquals(UnsignedBigInteger(4), UnsignedBigInteger(2) + UnsignedBigInteger(2))
        assertEquals(
            UnsignedBigInteger("9087654330"),
            UnsignedBigInteger("9087654329") + UnsignedBigInteger(1)
        )
    }

    @Test
    @Tag("8")
    fun minus() {
        assertEquals(UnsignedBigInteger(2), UnsignedBigInteger(4) - UnsignedBigInteger(2))
        assertEquals(UnsignedBigInteger("233200"), UnsignedBigInteger("235544") - UnsignedBigInteger("2344"))
        assertThrows(ArithmeticException::class.java) {
            UnsignedBigInteger(2) - UnsignedBigInteger(4)
        }
    }

    @Test
    @Tag("12")
    fun times() {
        assertEquals(
            UnsignedBigInteger("5"),
            UnsignedBigInteger("1") * UnsignedBigInteger("5")
        )
        assertEquals(UnsignedBigInteger("84"), UnsignedBigInteger("7") * UnsignedBigInteger("12"))
        assertEquals(
            UnsignedBigInteger(mutableListOf(1u, 0u)),
            UnsignedBigInteger("2147483648") * UnsignedBigInteger(2)
        )
        assertEquals(
            UnsignedBigInteger(mutableListOf(40u, 4456u, 68944u, 2334400u)),
            UnsignedBigInteger(mutableListOf(1u, 100u)) * UnsignedBigInteger(
                mutableListOf(40u, 456u, 23344u)
            )
        )
    }

    @Test
    @Tag("16")
    fun div() {
        assertThrows(ArithmeticException::class.java) { UnsignedBigInteger(100) / UnsignedBigInteger(0) }
        assertEquals(UnsignedBigInteger(500), UnsignedBigInteger(1000) / UnsignedBigInteger(2))
        assertEquals(
            UnsignedBigInteger("2147483648"),
            UnsignedBigInteger(mutableListOf(1u, 0u)) / UnsignedBigInteger(2)
        )
        assertEquals(
            UnsignedBigInteger(mutableListOf(17u, 3253756577u, 2072977049u)),
            UnsignedBigInteger(mutableListOf(2344u, 900u, 2345u, 5665u)) / UnsignedBigInteger(
                mutableListOf(
                    132u,
                    48558u
                )
            )
        )
    }

    @Test
    @Tag("16")
    fun rem() {
        assertEquals(
            UnsignedBigInteger(0),
            UnsignedBigInteger(20) % UnsignedBigInteger(2)
        )
    }

    @Test
    @Tag("8")
    fun equals() {
        assertEquals(UnsignedBigInteger(123456789), UnsignedBigInteger("123456789"))
        assertEquals(UnsignedBigInteger(mutableListOf(9u, 4294967287u)), UnsignedBigInteger("42949672951"))
    }

    @Test
    @Tag("8")
    fun compareTo() {
        assertTrue(UnsignedBigInteger(123456789) < UnsignedBigInteger("9876543210"))
        assertTrue(UnsignedBigInteger("9876543210") > UnsignedBigInteger(123456789))
        assertTrue(UnsignedBigInteger(mutableListOf(1u, 23u)) < UnsignedBigInteger(mutableListOf(1u, 2u, 3u)))
    }

    @Test
    @Tag("8")
    fun toInt() {
        assertEquals(123456789, UnsignedBigInteger("123456789").toInt())
        assertThrows(ArithmeticException::class.java) { UnsignedBigInteger("29399034899349349938493392932893").toInt() }
    }

    private fun UnsignedBigInteger.toBigInteger() = BigInteger("$this")

    @Test
    fun test() {
        val value = "16414891648916498218168937819263016316213221321"
        val actual = UnsignedBigInteger(value) * UnsignedBigInteger(value)
        val expected = BigInteger(value) * BigInteger(value)
        assertEquals(actual.toBigInteger(), expected)
    }

    @Test
    fun compareBigIntegerTimes() {
        val values = listOf(Pair("1", "6"), Pair("128594939283912849039", "24553"), Pair("0", "1"))

        for (i in values) {
            assertEquals(
                (UnsignedBigInteger(i.first) * UnsignedBigInteger(i.second)).toBigInteger(),
                BigInteger(i.first) * BigInteger(i.second)
            )
        }
    }

    @Test
    fun compareBigIntegerPlus() {
        val values = listOf(
            Pair("28349849384923929392099294030", "0"),
            Pair("283493", "399")
        )

        for (i in values) {
            assertEquals(
                (UnsignedBigInteger(i.first) + UnsignedBigInteger(i.second)).toBigInteger(),
                BigInteger(i.first) + BigInteger(i.second)
            )
        }
    }

    @Test
    fun compareBigIntegerDiv() {
        val values = listOf(
            Pair("928349438493293", "1"),
            Pair("928349438493293", "928349438493293"),
            Pair("24354354343", "234557"),
        )

        for (i in values) {
            assertEquals(
                (UnsignedBigInteger(i.first) / UnsignedBigInteger(i.second)).toBigInteger(),
                BigInteger(i.first) / BigInteger(i.second)
            )
        }
    }

    @Test
    fun compareBigIntegerMinus() {
        val values = listOf(Pair("2754397", "238"), Pair("1", "1"), Pair("293495403894992939", "999999999999"))
        for (i in values) {
            assertEquals(
                (UnsignedBigInteger(i.first) - UnsignedBigInteger(i.second)).toBigInteger(),
                BigInteger(i.first) - BigInteger(i.second)
            )
        }
    }

}