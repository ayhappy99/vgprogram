package kr.ac.hs.vgprogram;

public class UserAccount
{
    private String idToken;   //파이어베이스 uid (고유 토큰정보)
    private String email;
    private String Pwd;
    private String Name;
    private String PwdCheck;
    private String Id;

    public UserAccount()   {  }


    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPwd()
    {
        return Pwd;
    }

    public void setPwd(String pwd)
    {
        Pwd = pwd;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public String getPwdCheck()
    {
        return PwdCheck;
    }

    public void setPwdCheck(String pwdCheck)
    {
        PwdCheck = pwdCheck;
    }

    public String getId()
    {
        return Id;
    }

    public void setId(String id)
    {
        Id = id;
    }
}
