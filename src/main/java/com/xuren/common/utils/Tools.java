package com.xuren.common.utils;


import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

/**
 * ����:1.��ȡ��Դ�����ļ��� 2.��ȡ��Դ�����ļ��ľ����ֶΣ� 3.���PreparedStatement��
 */
public class Tools {

	/**���ڶ�ȡ��Դ�����ļ���bundle*/
	private static Properties properties;	

	
	/**
	 * ��ʼ�������ļ���·������ȡ���µ�properties
	 */
	public void getProperties(String file){
		try{
			properties = new Properties();
//			FileInputStream fis = new FileInputStream(new File(file));
			InputStream is = Tools.class.getClassLoader().getResourceAsStream(file);
			properties.load(is);
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage() + " " + file);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ��Դ�����ļ��ﶨ����ֶΣ����磺key = xxx
	 */
	public String getStringFromProperty(String key){
		String str = null;
		if(properties != null){
			str = properties.getProperty(key);
		}
		return str;
	}

	/**
	 * ���PreparedStatement�е�δ֪��
	 * @return ���������PreparedStatement
	 */
	public static PreparedStatement setPreStatementItems(PreparedStatement preStat, Object... inserts){
		try {
			if(inserts == null){
				return preStat;
			}
			int i = 1;
			for (Object v : inserts) {
				if(v.getClass().getSimpleName().equals("Integer")){//�ж�������
					preStat.setInt(i,(Integer)v);
					i++;
				}               	
				else if(v.getClass().getSimpleName().equals("String")){//�ж����ַ���
					preStat.setString(i, (String)v);
					i++;
				}
				else if(v.getClass().getSimpleName().equals("Long")){
					preStat.setLong(i, (long)v);
					i++;
				}
				else
					continue;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return preStat;
	}
	
	/**
	 * ��ȡ�ַ�������
	 */
	 public static int getWordCount(String s)
	    {
	        int length = 0;
	        for(int i = 0; i < s.length(); i++)
	        {
	            int ascii = Character.codePointAt(s, i);
	            if(ascii >= 0 && ascii <=255)
	                length++;
	            else
	                length += 2;
	                
	        }
	        return length;
	    }
	
	/**
	 * ���һ��������� 
	 */
	public static int[] getRandomSequence(int rangeSize, int queueSize){
		if(rangeSize < queueSize){
			return null;
		}
		Random rd = new Random();
		int[] sequence = new int[rangeSize];
		for (int i = 0; i < rangeSize; i++)
			sequence[i] = i;
		int[] output = new int[queueSize];
		int end = rangeSize-1;
		for (int i = 0; i < queueSize; i++){
			int num = rd.nextInt(end + 1);
			output[i] = sequence[num];
			sequence[num] = sequence[end];
			end--;
		}
		return output;
	}
}