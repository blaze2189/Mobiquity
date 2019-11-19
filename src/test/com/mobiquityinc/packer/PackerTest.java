package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PackerTest {

    private final String testFileRoute = "/Users/jorgeangellopezcorona/Downloads/MobEu-Hiring-Java/src/test/resources/";

    @Test
    public void testPack() throws APIException {
        String path = testFileRoute+"test.csv";
        String expected = "4 \n-\n2 7 \n2 8 \n2 5 6 7 8 ";
        String result;
        try {
            result = Packer.pack(path);
            System.out.println(result);
        } catch (APIException ape) {
            throw ape;
        }
        assertEquals(expected, result);
    }

    @Test(expected = APIException.class)
    public void testPackInvalidFile() throws APIException {
        String path = testFileRoute+"testInvalid.csv";
        try {
            Packer.pack(path);
        } catch (APIException e) {
            throw e;
        }
    }

    @Test(expected = APIException.class)
    public void testPackInvalidFile2() throws APIException {
        String path = testFileRoute+"testInvalid2.csv";
        try {
            Packer.pack(path);
        } catch (APIException e) {
            throw e;
        }
    }

    @Test(expected = APIException.class)
    public void testPackInvalidFile3() throws APIException {
        String path = testFileRoute+"testInvalid3.csv";
        try {
            Packer.pack(path);
        } catch (APIException e) {
            throw e;
        }
    }
}