package service;

import com.vdurmont.emoji.EmojiParser;

public class test  {
    public String getemoji(){
        String str = "An :grinning:awesome :smiley:string &#128516;with a few :wink:emojis!";
//        String result = EmojiParser.parseToUnicode(str);
        String result = "An &#x1f600;awesome &#x1f603;string with a few &#x1f609;emojis!";
        return result;
    }
}
