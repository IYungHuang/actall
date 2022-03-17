package tw.tigercloud2022.youract;

public class M_gPost {
    public String mWebsite;
    public String mMap;
    public String mDescription;
    public String mName;
    public String mPicture;
    public String mLocation;
    public String mStart;
    public String id;

    public M_gPost(String mName, String mPicture1, String mLocation, String mStart, String mDescription, String mMap, String mWebsite ,String id) {
        this.mName=mName;
        this.mPicture=mPicture1;
        this.mLocation=mLocation;
        this.mStart=mStart;
        this.mDescription=mDescription;
        this.mMap=mMap;
        this.mWebsite=mWebsite;
        this.id=id;

    }

}
