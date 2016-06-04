package com.leagueofsummoners.model.utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import com.leagueofsummoners.MailConfig;


/*
Autores= Juan José Ramírez & Isidoro Martín
Fecha= Junio de 2016
Licencia=  gp130
Version= 1.0
Descripcion= Proyecto final desarrollo de aplicaciones web. League of Summoners es una aplicación
enfocada a los jugadores del popular juego League of Legends, usando esta aplicación podrán acceder
a guías, detalles sobre campeones e incluso sus últimas partidas.

Copyright (C) 2016 Juan José Ramírez & Isidoro Martín
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/**
 * Esta clase sirve para enviar correo electrónico
 *
 */
public class MailUtils {

	private static Properties props;

	static {
		props = System.getProperties();
		props.put(MailConfig.EMAIL_SMPTP_AUTH, MailConfig.EMAIL_SMPTP_AUTH_VALUE);
		props.put(MailConfig.EMAIL_SMPTP_TTLS, MailConfig.EMAIL_SMPTP_TTLS_VALUE);
		props.put(MailConfig.EMAIL_SMPTP_HOST, MailConfig.EMAIL_SMPTP_HOST_VALUE);
		props.put(MailConfig.EMAIL_SMPTP_PORT, MailConfig.EMAIL_SMPTP_PORT_VALUE);
	}

	public static boolean sendEmail(String to, String subject, String content) {
		boolean sent = true;
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MailConfig.EMAIL_USERNAME, MailConfig.EMAIL_PASSWORD);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(MailConfig.EMAIL_USERNAME));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(content);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
			sent = false;
		}
		return sent;
	}
}