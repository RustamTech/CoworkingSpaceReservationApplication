package org.example.CustomException;
/*
IF we're creating checked exception we should extend Exception class
IF we're creating unchecked exception we should extend RuntimeException class
*/
//first our custom exception will be about NotAuthorizedUser(not login)
//second our exception about empty UserList and also empty adminList
public class CustomException {
    public static class NotAuthorizedUser extends  Exception{
        public NotAuthorizedUser(String message){
            super(message);
        }
    }

    public static class EmptyUserList extends Exception{
        public EmptyUserList(String message){
            super(message);
        }
    }

    public static class EmptyAdminList extends Exception{
        public EmptyAdminList(String message){
            super(message);
        }
    }
}
