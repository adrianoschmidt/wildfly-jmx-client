package br.com.localhost8080.wildlfly.jmx;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * A client JMX to test the connection with WildFly
 * I got the initial source at https://docs.jboss.org/author/display/WFLY8/JMX+subsystem+configuration
 */
public class JMXExample {

	public static void main(String[] args) throws Exception {
		// Get a connection to the WildFly 8 MBean server on localhost
		String host = "localhost";
		// String host = "192.168.0.35";
		int port = 9990; // management-web port

		String urlString = System.getProperty("jmx.service.url", "service:jmx:http-remoting-jmx://" + host + ":" + port);
		JMXServiceURL serviceURL = new JMXServiceURL(urlString);
		JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, null);
		MBeanServerConnection connection = jmxConnector.getMBeanServerConnection();

		// Invoke on the WildFly 8 MBean server
		int count = connection.getMBeanCount();
		System.out.println(count);
		jmxConnector.close();
	}
}