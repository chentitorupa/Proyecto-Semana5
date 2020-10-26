package com.charalito.petagramv2.email;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail extends AsyncTask<Void,Void,Void> {

    //Declarando variables

    private Context context;
    private Session session;

    //Información para enviar por email
    private String email;
    private String subject;
    private String message;

    //Diálogo del progreso para mostrar mientras enviamos el correo
    private ProgressDialog progressDialog;


    //Constructor de Clase
    public SendMail(Context context, String email, String subject, String message) {
        //Inicializando variables
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Mostrando dialogo del proceso mientras enviamos el correo

        progressDialog = ProgressDialog.show(context,"Sending message" , "Please wait...",false,false);

    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        //descartando el progreso de diálogo
        progressDialog.dismiss();

        //Mostrando mensaje exitoso
        Toast.makeText(context, "Mensaje Enviado",Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        //Creando propiedades
        Properties props = new Properties();

        //Configurando propiedades para gmail
        //Si tu no estas usando gmail, tu podrías necesitar cambiar los valores
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //Creando una nueva sesion
        session = Session.getDefaultInstance(props, new Authenticator() {
            //autenticando la contraseña
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Config.EMAIL,Config.PASSWORD);
            }
        });

        try {
            //Creando Objecto MimeMessage
            MimeMessage mm = new MimeMessage(session);

            //Modificando dirección del remitente
            mm.setFrom(new InternetAddress(Config.EMAIL));

            //Agregando receptor
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            //Agregando sujeto
            mm.setSubject(subject);

            //Agregando mensaje
            mm.setText(message);

            //Enviando correo
            Transport.send(mm);

        } catch (MessagingException e){
            e.printStackTrace();
        }

        return null;
    }
}

