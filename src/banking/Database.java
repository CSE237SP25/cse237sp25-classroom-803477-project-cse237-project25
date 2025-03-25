package banking;
import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;

public class Database {
    private Map<String, User> mapToUser;
    
    public Database(){
        mapToUser = new HashMap<>();

    }
    public boolean doesUserExist(String username){
        return mapToUser.containsKey(username);
    }

    public User getUserData(String username){
        if(mapToUser.containsKey(username))return mapToUser.get(username);
        return null;
    }

    public User createUser(User newUser){
        mapToUser.put(newUser.getUsername(), newUser);
        return newUser;


    }
    
    public void deleteUser(String username) throws Exception {
        if (mapToUser.containsKey(username)) {
            mapToUser.remove(username);
        } else {
            throw new RuntimeException("Username: '" + username + "' not found in the database.");
        }
    }
    

    
}
