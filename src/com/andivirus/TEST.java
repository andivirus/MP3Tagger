package com.andivirus;

/**
 * Created by andiv_000 on 23.06.2015.
 */

import java.io.File;
        import java.io.IOException;
        import com.beaglebuddy.mp3.MP3;
        import com.beaglebuddy.id3.enums.Genre;
        import com.beaglebuddy.id3.enums.PictureType;

public class TEST
{
    public static void main(String[] args)
    {
        try
        {
            MP3 mp3 = new MP3("C:\\Users\\andiv_000\\Music\\Arch Enemy - War Eternal (2014)\\03 - War Eternal.mp3");

            // if there was any invalid information (ie, ID3v2.x frames) in the .mp3 file,
            // then display the errors to the user
            if (mp3.hasErrors())
            {
                mp3.displayErrors(System.out);      // display the errors that were found
                                                    // discard the invalid information (ID3v2.x frames) and
            }                                      // save only the valid frames back to the .mp3 file

            System.out.println(mp3.getAlbum());
        }
        catch (IOException ex)
        {

            ex.printStackTrace();
            System.out.println("An error occurred while reading/saving the mp3 file.");
        }

        //TESTCOMMIT
    }
}