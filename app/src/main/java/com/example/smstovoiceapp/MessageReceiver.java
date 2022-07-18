package com.example.smstovoiceapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class MessageReceiver extends BroadcastReceiver {
    TextToSpeech textToSpeech;
    private static final Pattern amountPattern = Pattern.compile("\\d+\\.\\d{2} ");
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle=intent.getExtras();
            SmsMessage[]msgs;
            String msg_from;
            if(bundle!=null){
                Toast.makeText(context,"message came",Toast.LENGTH_SHORT).show();
//                try{
//                    Object[]pdus=(Object[])bundle.get("pdus");
//                    msgs=new SmsMessage[pdus.length];
//                    for(int i=0;i< msgs.length;i++){
//                        msgs[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
//                        msg_from=msgs[i].getOriginatingAddress();
//                        final String msgBody=msgs[i].getMessageBody();
//                        textToSpeech = new TextToSpeech(context.getApplicationContext(), new TextToSpeech.OnInitListener() {
//                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                            @Override
//                            public void onInit(int status) {
//                                textToSpeech.setLanguage(Locale.forLanguageTag("hin"));
//                                textToSpeech.speak(msgBody, TextToSpeech.QUEUE_FLUSH, null, null);
//                            }
//                        });
//                        Toast.makeText(context, "from "+msg_from+" ,Body: "+msgBody, Toast.LENGTH_SHORT).show();
//                    }
//                }
//                catch (Exception e ){
//                    e.printStackTrace();
//                }
                speak("hello world", context);
            }
        }
        Toast.makeText(context, "SMS receivedp", Toast.LENGTH_SHORT).show();
    }

//    private String parse(String message) {
////        message = message.toLowerCase();
////        message = message.replaceAll(",", "");
////        String action;
////        if (message.contains("debited")) {
////            action = "Debited. ";
////        } else if (message.contains("paid") || message.contains("credited")) {
////            action = "Credited. ";
////        } else {
////            return null;
////        }
////        Matcher matcher = amountPattern.matcher(message);
////        if (!matcher.find()) {
////            return null;
////        }
////        String amount = matcher.group(0);
////        String amountInRupees = amount.split("\\.")[0];
////        String parsedMessage = action + amountInRupees + " rupees.";
////        return parsedMessage;
//        return message;
//    }

    public static final String MESSAGE = "message";
    public static final double speechRate = 0.9;
public void speak(String speech, Context context) {
    if (textToSpeech == null) {
        initTTS(context);
    }
    int speechStatus = textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);

    if (speechStatus == TextToSpeech.ERROR) {
        Log.e("TTS", "Error in converting Text to Speech!");
    }
}

    private void initTTS(Context context) {
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int ttsLang = textToSpeech.setLanguage(Locale.US);

                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                            || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "The Language is not supported!");
                    } else {
                        Log.i("TTS", "Language Supported.");
                    }
                    Log.i("TTS", "Initialization success.");
                } else {
                    Log.d("TTS", "TTS failed");
//                    Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textToSpeech.setSpeechRate((float) speechRate);
    }
}
