package com.sachin.example.timesofindia;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

   class ParseApplication {
    private static final String TAG = "ParseApplications";
    private ArrayList<FeedEntry> applications;

    ParseApplication() {
        this.applications = new ArrayList<>();
    }

     ArrayList<FeedEntry> getApplications() {

        return applications;
    }

     boolean parse(String xmlData) {
        boolean status = true;
        FeedEntry currentRecord = null;
        boolean inEntry = false;
        String textValue = "";

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:

                        if ("item".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                                currentRecord = new FeedEntry();
//
                        }
                        break;

                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:


                        if (inEntry) {
                            if ("item".equalsIgnoreCase(tagName)) {
                                applications.add(currentRecord);
                                inEntry = false;
                            } else if ("title".equalsIgnoreCase(tagName)) {
                                currentRecord.setTitle(textValue);
                            } else if ("description".equalsIgnoreCase(tagName)) {

                                textValue = textValue.substring(textValue.indexOf("a>") + 1);
                                currentRecord.setDesc(textValue);
                            } else if ("pubdate".equalsIgnoreCase(tagName)) {
                                currentRecord.setPubDate(textValue);
                            }
                            break;
                        }

                    default:
                        // Nothing else to do.
                }
                eventType = xpp.next();

            }

        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }

        return status;
    }
}