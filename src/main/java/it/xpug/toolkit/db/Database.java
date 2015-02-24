package it.xpug.toolkit.db;

import java.sql.*;
import java.util.Map;
import java.util.Map.Entry;

public class Database {
	private DatabaseConfiguration configuration;

	public Database(DatabaseConfiguration configuration) {
		this.configuration = configuration;
	}

	public void execute(String sql, Object... params) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			setParams(statement, params);
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(statement);
			close(connection);
		}
	}

	private Connection getConnection() throws SQLException {
		Connection connection = configuration.getConnection();
		connection.setAutoCommit(false);
		return connection;
	}

	public ListOfRows select(String sql, Object... params) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			setParams(statement, params);
			resultSet = statement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			ListOfRows result = new ListOfRows();
			while (resultSet.next()) {
				result.newRow();
				for (int i=0; i<metaData.getColumnCount(); i++) {
					String columnName = metaData.getColumnName(i+1);
					result.put(columnName, resultSet.getObject(columnName));
				}
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(statement);
			close(connection);
		}
	}

	public Object selectOneValue(String sql) {
		ListOfRows rows = select(sql);
		Map<String, Object> map = rows.get(0);
		Entry<String, Object> entry = map.entrySet().iterator().next();
		return entry.getValue();
	}

	private void setParams(PreparedStatement statement, Object... params) throws SQLException {
		for (int i = 0; i < params.length; i++) {
			statement.setObject(i + 1, params[i]);
		}
	}

	private void close(ResultSet resultSet) {
		if (null != resultSet) {
			try {
				resultSet.close();
			} catch (Exception ignored) {}
		}
	}

	private void close(Statement statement) {
		if (null != statement) {
			try {
				statement.close();
			} catch (Exception ignored) {}
		}
	}

	private void close(Connection connection) {
		if (null != connection) {
			try {
				connection.close();
			} catch (Exception ignored) {}
		}
	}

}
