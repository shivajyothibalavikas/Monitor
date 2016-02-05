package com.kenscio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.ChannelSftp.LsEntry;

public class Run implements Runnable{
	int len=0;
	List<String> alold = new ArrayList<String>();
	public void run()
	{
		while(true)
		{
			ChannelSftp channel = SFTPConnect.getConnection();
			try {
				List<String> alnew = new ArrayList<String>();
				channel.cd("dump");
				Vector v = new Vector();
				v = channel.ls(channel.pwd());
				Iterator i = v.iterator();
				boolean flag = false;
				String folder = null;
				while(i.hasNext())
				{
					LsEntry entry =  (LsEntry) i.next(); 
					alnew.add(entry.getFilename());
				}
				int newLen = alnew.size();
				if(len!=newLen)
				{
					if(newLen-len>0)
					{
						System.out.println(newLen-len + " Files added");
					}
					else
					{
						System.out.println(len-newLen + " Files removed");
					}
					len=newLen;
					for(String ele : alnew)
					{
						if(alold.contains(ele))
						{
							continue;
						}
						else
						{
							System.out.println(ele);
							
						}
					}
					alold.clear();
					alold.addAll(alnew);
				}
				else
				{
					System.out.println("no files added");
				}
				
			} catch (SftpException e1) {
				e1.printStackTrace();
			}
			
			try 
			{
				Thread.sleep(5*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

}
