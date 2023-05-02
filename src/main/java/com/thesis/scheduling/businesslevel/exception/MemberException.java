package com.thesis.scheduling.businesslevel.exception;

public class MemberException extends BaseException {

	private static final long serialVersionUID = 1L;

	public MemberException(String code) {
        super("Member." + code);
    }    
	
	public static MemberException notFound() {
        return new MemberException("member.not.found");
    }
	
	//Login Exception
    public static MemberException loginFailUsernameAndPasswordWrong() {
        return new MemberException("login.usernameAndPassword.Wrong");
    }

    public static MemberException loginFailUsernameNotFound() {
        return new MemberException("login.username.NotFound");
    }
    
    public static MemberException loginFailPasswordIncorrect() {
        return new MemberException("login.password.incorrect");
    }

    public static MemberException loginMemberNotActive() {
        return new MemberException("login.member.NotActive");
    }
	
	//Create Exception
    public static MemberException createTitleNull() {
        return new MemberException("create.title.null");
    }
    
    public static MemberException createTHFirstNameNull() {
        return new MemberException("create.th first name.null");
    }
    
    public static MemberException createTHLastNameNull() {
        return new MemberException("create.th last name.null");
    }
    
    public static MemberException createUsernameNull() {
        return new MemberException("create.username.null");
    }
    
    public static MemberException createPasswordNull() {
        return new MemberException("create.password.null");
    }
    
    public static MemberException createUsernameDuplicated() {
        return new MemberException("create.username.duplicated");
    }
	


}
