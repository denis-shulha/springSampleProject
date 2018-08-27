package tst.springSample.logic;


public class ArticleImpl extends SpeachImpl implements Speach{

    @Override
    public void content() {
        super.content();
        System.out.println("alternative content");
    }
}
