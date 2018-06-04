package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class EntryTest {


    @Test
    public void newEntry_instantiatesCorrectly_true() throws Exception {
        Entry testEntry = new Entry("Kristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        assertTrue(testEntry instanceof Entry);
    }

    @Test
    public void newEntry_instantiatesNameCorrectly_KristenChellis() {
        Entry testEntry = new Entry("Kristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        assertEquals("Kristen Chellis", testEntry.getName());
    }

    @Test
    public void newEntry_instantiatesPhoneNumberCorrectly_5037204196() {
        Entry testEntry = new Entry("Kristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        assertEquals("503-720-4196", testEntry.getPhoneNumber());
    }

    @Test
    public void newEntry_instantiatesMailingAddressCorrectly_longstring() {
        Entry testEntry = new Entry("Kristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        assertEquals("5226 N Amherst St. Portland, OR 97203", testEntry.getMailingAddress());
    }

    @Test
    public void newEntry_instantiatesEmailAddressCorrectly_peanutster() {
        Entry testEntry = new Entry("Kristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        assertEquals("peanutster@gmail.com", testEntry.getEmailAddress());
    }

    @Test
    public void setName() {
        Entry testEntry = new Entry("Kristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        testEntry.setName("Matt Dowdney");
        assertEquals("Matt Dowdney", testEntry.getName());
    }

    @Test
    public void setPhoneNumber() {
        Entry testEntry = new Entry("Kristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        testEntry.setPhoneNumber("503-706-6481");
        assertEquals("503-706-6481", testEntry.getPhoneNumber());
    }



    @Test
    public void setMailingAddress() {
        Entry testEntry = new Entry("Kristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        testEntry.setMailingAddress("1849 N Russet St. Portland, OR 97217");
        assertEquals("1849 N Russet St. Portland, OR 97217", testEntry.getMailingAddress());
    }



    @Test
    public void setEmailAddress() {
        Entry testEntry = new Entry("Kristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        testEntry.setEmailAddress("k.n.chellis@gmail.com");
        assertEquals("k.n.chellis@gmail.com", testEntry.getEmailAddress());
    }



    @Test
    public void setId() {
        Entry testEntry = new Entry("Kristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        testEntry.setId(1);
        assertEquals(1, testEntry.getId());
    }
}