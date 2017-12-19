package com.startrak;

import java.util.Hashtable;
import java.sql.Timestamp;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.startrak.gprs.GprsDeliverMessage;
import com.startrak.gprs.GprsDeliverMessage.SourceType;
 

public class GSMMsgProducer {

	/** SENTRY **/
	
	private static final String PROVIDER_URL = "ormi://192.168.1.109:23791/GP_GSM_Sentry";
	private static final String ADMIN_USER = "oc4jadmin";
	private static final String ADMIN_PASS = "809ecar10g";
	private static final String CONN_CLASS = "com.evermind.server.rmi.RMIInitialContextFactory";
	
	private static final String QUEUE_NAME = "gsmmsgs/sentry";
	private static final String QUEUE_CONNECTOR = "jms/RawQConnectionFactory";
	
	public QueueConnection connection;
	public QueueConnectionFactory factory;
	public Queue queue;
	public QueueSender sender;
	public QueueSession session;

	
	public static void main(String[] args) {
		
		String rtSn= "GCTH004413";
		String payLoad= "38BAC77D40E291130140E400646480104A5216191DAD50CB000000A08E0D11E06A4A10003CC003000500044000401940FA40FF80FEBFD9FDFFFFFF3F000080FFFBBFFFFB3F88037B60140000000016191DAD50CBFFFF5323F4C75E995C91DEA17B00";
		GSMMsgProducer gsmProducer = new GSMMsgProducer();
		gsmProducer.jmsInit();		
		sendMessage(rtSn, payLoad, gsmProducer);
	}
	
	public static void sendMessage(String unitId, String payload, GSMMsgProducer gsmProducer) {
		
		 GprsDeliverMessage gprsMessage= new GprsDeliverMessage();
		 gprsMessage.setCorrelationId((short)1);
		 
		 gprsMessage.setMessageData(getByteArrayFromHex(payload));
		 gprsMessage.setMessageDataHex(payload);
		 gprsMessage.setUnitId(unitId);
		 gprsMessage.setProtocolId(	(short)4);
		 gprsMessage.setGeneratedStamp(new Timestamp(System.currentTimeMillis()));
		 gprsMessage.setReceivedStamp(new Timestamp(System.currentTimeMillis()));
		 gprsMessage.setSrcType(SourceType.GPRS_HTTP); 
		 gprsMessage.setMessageType((short)8);
		 
		 try {
			 ObjectMessage obj = gsmProducer.session.createObjectMessage(gprsMessage);
			 gsmProducer.sender.send(obj);
			 System.out.println("Test Message Sent");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	private Context getInitialContext() throws NamingException {
		Hashtable<String,String> env = new Hashtable<String,String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, CONN_CLASS);
		env.put(Context.SECURITY_PRINCIPAL, ADMIN_USER);
		env.put(Context.SECURITY_CREDENTIALS, ADMIN_PASS);
		env.put(Context.PROVIDER_URL, PROVIDER_URL);	
		return new InitialContext(env);
	}
	
	public static byte[] getByteArrayFromHex(String hex) {
		byte[] data = new byte[hex.length() / 2];
		for (int i = 0; i < (hex.length() / 2); i++) {
			int index = 2 * i;
			byte val = (byte) Integer.parseInt(hex.substring(index, index + 2), 16);
			data[i] = val;
		}
		return data;
	}
	
	public void jmsInit() {
		try {
			System.out.println("Inside JMSINit");
			Context ctx = getInitialContext();
			factory = (QueueConnectionFactory) ctx.lookup(QUEUE_CONNECTOR);
	    	queue = (Queue) ctx.lookup(QUEUE_NAME); 			
			connection = factory.createQueueConnection();
			session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			sender = session.createSender(queue); 
			connection.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
