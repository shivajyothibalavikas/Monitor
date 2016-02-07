package com.kenscio;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.SftpATTRS;

public class Run implements Runnable {
	int len = 0;
	List<String> alold = new ArrayList<String>();

	@SuppressWarnings("unchecked")
	public void run()
	{
		while(true)
		{
			ChannelSftp channel = SFTPConnect.getConnection();
			try {
				List<String> alnew = new ArrayList<String>();
				channel.cd("dump");
				Vector<LsEntry> v = new Vector<LsEntry>();
				v = channel.ls("*");
				Iterator<LsEntry> i = v.iterator();
				while(i.hasNext())
				{
					LsEntry entry =  (LsEntry) i.next(); 
					SftpATTRS attributes = entry.getAttrs();
					if(!attributes.isDir())
					{
						alnew.add(entry.getFilename());
					}
				}
				int newLen = alnew.size();
				if(len!=newLen)
				{
					if(newLen-len>0)
					{
						System.out.println(newLen-len + " Files added:");
						for(String ele : alnew)
						{
							if(alold.contains(ele))
							{
								continue;
							}
							else
							{
								System.out.println(ele);
								if(ele.endsWith(".json"))
								{
									InputStream is = channel.get(ele);
									Boolean flag = Parse.parse(is);
									if(flag)
									{
										System.out.println("contents added to DB succesfully");
									}
									else
									{
										System.out.println("Error while parsing");
									}
								}
							}
						}
					}
					else
					{
						System.out.println(len-newLen + " Files removed:");
						for(String ele : alold)
						{
							if(alnew.contains(ele))
							{
								continue;
							}
							else
							{
								System.out.println(ele);
								
							}
						}
					}
					len=newLen;
					alold.clear();
					alold.addAll(alnew);
				}
				else
				{
					System.out.println("no files added or removed");
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
