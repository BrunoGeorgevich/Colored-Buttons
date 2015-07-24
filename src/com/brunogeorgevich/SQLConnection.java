package com.brunogeorgevich;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLConnection {

	private Connection connect = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;

	private String tableName = "";

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public SQLConnection(String dbName, String username, String password) throws SQLException{

		boolean internetConected = (internetIsConnected("youtube.com") || internetIsConnected("google.com") || internetIsConnected("amazon.com"));

		if(internetConected) {
			try {
				//Carrega o driver especificado
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Driver não encontrado!"+e);
			}

			//Cria a conexão com o banco de dados;
			connect = DriverManager.getConnection("jdbc:mysql://" + dbName , username, password);
		} else {
			System.out.println("Por favor, conecte-se a internet!");
		}

	}

	public boolean insertInto(String [] values) throws SQLException {

		String query = "insert into " + tableName + " values (";

		for(int i = 0; i < values.length; i++) {
			query += values[i];

			if(i != values.length -1) {
				query += ",";
			}
		}

		query += ")";

		preparedStatement = connect.prepareStatement(query);
		preparedStatement.executeUpdate();

		return true;

	}

	public boolean deleteFrom(String attr, String value) throws SQLException {

		//		Remoção de estado
		preparedStatement = connect.prepareStatement("delete from " + tableName + " where " + attr + "= ? ; ");
		preparedStatement.setString(1, value);
		preparedStatement.executeUpdate();

		return true;
	}

	public ArrayList<ArrayList<String>> getResults(String [] columns) throws SQLException {

		preparedStatement = connect.prepareStatement("SELECT * from " + tableName);
		resultSet = preparedStatement.executeQuery();

		ArrayList<ArrayList<String>> aux = new ArrayList<>();

		while(resultSet.next()){

			ArrayList<String> aux2 = new ArrayList<String>();

			for(int i = 0; i < columns.length; i++) {
				aux2.add(resultSet.getString(columns[i]));
			}

			aux.add(aux2);
		}

		return aux;

	}

	public String getResultByAttr(String attr, String value) throws SQLException {

		preparedStatement = connect.prepareStatement("SELECT " + attr +" from " + tableName + " where " + attr + "=\"" + value + "\"");
		resultSet = preparedStatement.executeQuery();

		if(!resultSet.next())
			return "";

		return resultSet.getString(1);
	}

	public String getResultByAttr(String firstAttr, String secondAttr, String value) throws SQLException {

		preparedStatement = connect.prepareStatement("SELECT " + firstAttr +" from " + tableName + " where " + secondAttr + "=\"" + value + "\"");
		resultSet = preparedStatement.executeQuery();

		if(!resultSet.next())
			return "";

		return resultSet.getString(1);
	}

	public void close() {
		try {

			boolean switcher = false;

			if (resultSet != null) { 
				switcher = true;
				resultSet.close();
			}
			if (connect != null) { 
				switcher = true;
				connect.close();
			}

			if(switcher)
				System.out.println("Closed connection with MySQL!");

		} catch (Exception e) {

			System.out.println("ERROR CLOSE FUNCTION!!");
			e.printStackTrace();

		}
	}

	/*
	 * Utilidades
	 */
	
	public static String encrypt(String passwordToHash) throws Exception {

		String generatedPassword = null;

		try {
			//Cria uma instancia do MessageDigest SHA-256 
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			//Adiciona os bytes da senha ao digest
			md.update(passwordToHash.getBytes());

			//Pega os bytes encriptados
			byte[] bytes = md.digest();

			//Os bytes estão em Decimal, converteremos eles para HexaDecimal
			StringBuilder sb = new StringBuilder();

			for(int i=0; i< bytes.length ;i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			//Agora temos a senha completa em Hexa
			generatedPassword = sb.toString();
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}

		return generatedPassword;

	}

	public static boolean internetIsConnected(String site) {
		Socket sock = new Socket();
		InetSocketAddress addr = new InetSocketAddress(site,80);
		try {
			sock.connect(addr,3000);
			return true;
		} catch (IOException e) {
			return false;
		} finally {
			try {sock.close();}
			catch (IOException e) {}
		}
	}
} 