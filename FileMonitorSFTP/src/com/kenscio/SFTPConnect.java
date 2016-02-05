package com.kenscio;

import java.util.Properties;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SFTPConnect {
	
	public static ChannelSftp getConnection()
	{
		
		String user="sftp_demo";
		String ip="5.9.80.147";
		String psw = "sftpdemo123";
		ChannelSftp sftpChannel = null;
		
		JSch jSch = new JSch();
		Session session;
		try 
		{
			session = jSch.getSession(user,ip);
			session.setPassword(psw);
			Properties config = new Properties();
	        config.put("StrictHostKeyChecking", "no");
	        session.setConfig(config);
			session.connect();
			sftpChannel = (ChannelSftp) session.openChannel("sftp");
			sftpChannel.connect();

		} 
		catch (JSchException e) 
		{
			System.out.println("Exception" + e);
		}
		
		return sftpChannel;
		
	}

}
