package dao;

import models.Entry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.*;
import org.sql2o.Connection;

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
}




