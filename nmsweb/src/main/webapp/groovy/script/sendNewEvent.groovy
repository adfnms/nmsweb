#!/usr/bin/env groovy

import groovy.xml.MarkupBuilder;

class sendEvent {

  static void sendNewSuspectEvent(String[] args) {

      def openNmsHost = "localhost";
      if (args.length > 0) {
         openNmsHost = args[0];
      }

//        println " Let's print Asterik"

      Socket socket = new Socket("192.168.0.10", 5817);
//    Socket socket = new Socket("127.0.0.1", 5817);
      socket.outputStream.withWriter { out ->
//              System.out.withWriter { out ->
      
              def xml = new MarkupBuilder(out);
              xml.log {
                  events {
                      event {
                          uei("uei.opennms.org/internal/discoveryConfigChange")
                          source("sendEvent-groovy")
                          time(new Date())
                          host(InetAddress.getLocalHost().hostName)
                          
                      }
                  }
              }

      
     }
      
  }

}
