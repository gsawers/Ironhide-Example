package sawers.gregory.musicmaker;

/**
 * Created by Gregory.Sawers on 7/3/2014.
 */
public class MenuItem {

    private int imageId;
    private String title;
    private String desc;

    public MenuItem(int imagId, String nxtTitle, String nxtDesc){
        imageId = imagId;
        title = nxtTitle;
        desc = nxtDesc;
    }

    public int getImageId(){
        return imageId;
    }

    public void setImageid(int nxtId){
        imageId = nxtId;
    }

    public String getDesc(){
        return desc;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String nxtTitle){
        title = nxtTitle;
    }

}
