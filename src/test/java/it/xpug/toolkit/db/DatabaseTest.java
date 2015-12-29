package it.xpug.toolkit.db;

import static org.junit.Assert.*;
import it.xpug.todolist.*;

import org.junit.*;


public class DatabaseTest {

	private TestDatabase database = new TestDatabase();

	@Before
	public void setUp() throws Exception {
		database.execute("drop table if exists prova");
		database.execute("create table prova( id serial, name varchar(255) );");
	}

	@Test
	public void testSelectSum() throws Exception {
		String sql = "select 3 + 4";
		assertEquals(7, database.selectOneValue(sql));
	}

	@Test(expected=RuntimeException.class)
	public void selectNonExistentValue() throws Exception {
		String sql = "select * from prova";
		database.selectOneValue(sql);
	}

	@Test
	public void testSelectOneFromTable() throws Exception {
		database.execute("insert into prova (name) values (?);", "pippo");
		assertEquals("pippo", database.selectOneValue("select name from prova"));
	}

	@Test
	public void testSelectAllFromTable() throws Exception {
		database.execute("insert into prova (name) values (?);", "pluto");
		ListOfRows rows = database.select("select * from prova where name like ?", "pl%");
		assertEquals(1, rows.size());
	}

}
