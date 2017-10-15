package ca.mcgill.ecse321.urlms.persistence;

/**
 * Created by ericvuong on 2017-10-08.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import ca.mcgill.ecse321.urlms.controller.Controller;
import ca.mcgill.ecse321.urlms.model.StaffManager;
import ca.mcgill.ecse321.urlms.model.StaffMember;
import ca.mcgill.ecse321.urlms.model.URLMS;

import android.content.res.XmlResourceParser;

import com.example.team8.urlms.R;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;

import static android.R.attr.name;
import static com.example.team8.urlms.R.id.toDisplay;

public  abstract class Persistence
{
//TODO
    // implement XML reader


     public static URLMS load(XmlResourceParser parser) {


        URLMS urlms = new URLMS(0);
        StaffManager sm = urlms.getStaffManager();

        int id = 0;
        String name;

        int eventType = -1;
        while(eventType!=XmlResourceParser.END_DOCUMENT)
        {
            if(eventType == XmlResourceParser.START_TAG)
            {
                String staffName = parser.getName();
                if(staffName.equals("staff")){
                    id = Integer.parseInt(parser.getAttributeValue(null, "id"));
                    while(eventType!=XmlResourceParser.TEXT){
                        try {
                            eventType = parser.next();
<<<<<<< HEAD
                            throw new XmlPullParserException("lol");
=======
                            throw new XmlPullParserException("");
>>>>>>> dev
                        }  catch (XmlPullParserException e) {
                            e.printStackTrace();
                        }  catch (IOException e){
                            e.printStackTrace();
                        }}
<<<<<<< HEAD
                    name+= parser.getText() + "\n";
=======
                    name = parser.getText();
                    sm.addStaffMember(name,id);
>>>>>>> dev

                }
            }
            //exception handling
            try {
                eventType = parser.next();
                throw new XmlPullParserException("lol");
            }  catch (XmlPullParserException e) {
                e.printStackTrace();
            }  catch (IOException e){
                e.printStackTrace();
            }
        }
<<<<<<< HEAD
        toDisplay.setText(name);
        //lol

=======
>>>>>>> dev

        return urlms;
    }




}
