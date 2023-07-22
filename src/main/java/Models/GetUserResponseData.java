package Models;

public class GetUserResponseData {

    public Data data;
    public Support support;
    public class Data{
        public int id;
        public String email;
        public String first_name;
        public String last_name;
        public String avatar;
    }
    public class Support{
        public String url;
        public String text;
    }
}
