package tst.springSample.logic;

import tst.springSample.annotations.DeprecatedClass;
import tst.springSample.annotations.InjectRandomInt;
import tst.springSample.annotations.PostProxy;

@DeprecatedClass(newImpl = ArticleImpl.class)
public class SpeachImpl implements Speach{

    @InjectRandomInt(min = 1,max = 15)
    private int code;

    private String phrase;

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public SpeachImpl() {
        phrase = "Hello, my name is Denis";
    }

    @PostProxy
    public void content() {
        System.out.println("title - " + code + "\n content: "+ phrase);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
