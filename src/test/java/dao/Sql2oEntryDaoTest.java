package dao;

import models.Entry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.*;
import org.sql2o.Connection;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oEntryDaoTest {
    private Sql2oEntryDao entryDao; //ignore me for now. We'll create this soon.
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        entryDao = new Sql2oEntryDao(sql2o); //ignore me for now
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingEntrySetsId() throws Exception {
        Entry testEntry = new Entry("Kristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        int originalEntryId= testEntry.getId();
        entryDao.add(testEntry);
        assertNotEquals(originalEntryId, testEntry.getId());
    }

    @Test
    public void existingEntryCanBeFound() throws Exception{
        Entry testEntry = new Entry("Kristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        entryDao.add(testEntry);
        Entry foundEntry = entryDao.findById(1);
        assertEquals(testEntry, foundEntry);
    }

    @Test
    public void noEntriesFound() throws Exception {
        List<Entry> allEntries = entryDao.getAll();
        assertEquals(0, allEntries.size());
    }

    @Test
    public void allEntriesAreFound() throws Exception{
        Entry testEntry = new Entry("Kristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        Entry bestEntry = new Entry("Fristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        entryDao.add(testEntry);
        entryDao.add(bestEntry);
        List<Entry> allEntries = entryDao.getAll();
        assertEquals(2, allEntries.size());
    }

    @Test
    public void entryIsUpdated() {
        Entry testEntry = new Entry("Kristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        entryDao.add(testEntry);
        entryDao.update(1,"Fristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        Entry updatedEntry = entryDao.findById(1);
        assertEquals("Fristen Chellis", updatedEntry.getName());
        assertEquals(1, entryDao.getAll().size());
    }

    @Test
    public void entryDeletedById() {
        Entry testEntry = new Entry("Kristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        Entry bestEntry = new Entry("Fristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        entryDao.add(testEntry);
        entryDao.add(bestEntry);
        entryDao.deleteById(1);
        List<Entry> allEntries =entryDao.getAll();
        assertEquals(1, allEntries.size());
        assertTrue(allEntries.contains(bestEntry));
        assertFalse(allEntries.contains(testEntry));
    }

    @Test
    public void allEntriesAreDeleted() {
        Entry testEntry = new Entry("Kristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        Entry bestEntry = new Entry("Fristen Chellis", "503-720-4196", "5226 N Amherst St. Portland, OR 97203", "peanutster@gmail.com");
        entryDao.add(testEntry);
        entryDao.add(bestEntry);
        entryDao.clearAllEntries();
        List<Entry> allEntries =entryDao.getAll();
        assertEquals(0, allEntries.size());
    }
}




