/** Represents a social network. The network has users, who follow other uesrs.
 *  Each user is an instance of the User class. */
public class Network {

    // Fields
    private User[] users;  // the users in this network (an array of User objects)
    private int userCount; // actual number of users in this network

    /** Creates a network with a given maximum number of users. */
    public Network(int maxUserCount) {
        this.users = new User[maxUserCount];
        this.userCount = 0;
    }

    /** Creates a network  with some users. The only purpose of this constructor is 
     *  to allow testing the toString and getUser methods, before implementing other methods. */
    public Network(int maxUserCount, boolean gettingStarted) {
        this(maxUserCount);
        users[0] = new User("Foo");
        users[1] = new User("Bar");
        users[2] = new User("Baz");
        userCount = 3;
    }

    public int getUserCount() {
        return this.userCount;
    }

    /** Finds in this network, and returns, the user that has the given name.
     *  If there is no such user, returns null.
     *  Notice that the method receives a String, and returns a User object. */
    public User getUser(String name) {
        int i = 0;
        while(i < userCount){
            if(users[i].getName().equals(name)) {
                User user1 = users[i];
                return user1;
            }
            i++;
        }
        return null;
    }

    /** Adds a new user with the given name to this network.
    *  If ths network is full, does nothing and returns false;
    *  If the given name is already a user in this network, does nothing and returns false;
    *  Otherwise, creates a new user with the given name, adds the user to this network, and returns true. */
    public boolean addUser(String name) {
        if(userCount == this.users.length) {
            return false;
        }
        int i = 0;
        while(i < this.users.length && users[i]!=null) {
            if(users[i].getName().equals(name)) {
                return false;
            }
            i++;
        }
        if(users[i] == null) {
            users[i] = new User(name);
            userCount++;
            return true;
        }
        return false;
    }

    /** Makes the user with name1 follow the user with name2. If successful, returns true.
     *  If any of the two names is not a user in this network,
     *  or if the "follows" addition failed for some reason, returns false. */
    public boolean addFollowee(String name1, String name2) {
        if(getUser(name1) == null || getUser(name2) == null) {
            return false;
        }
        if(name1.equals(name2)) {
        return false;
        }
        if(getUser(name1).addFollowee(name2) == false ) {
            return false;
        }
        return true;
    }
    
    /** For the user with the given name, recommends another user to follow. The recommended user is
     *  the user that has the maximal mutual number of followees as the user with the given name. */
    public String recommendWhoToFollow(String name) {
        User mostRecommendedUserToFollow = null;
        int count = 0, maxValue = 0;
        User user1 = this.getUser(name);
        for(int i = 0; i < userCount; i++) {
            if(users[i].getName().equals(name)) {
                continue;
            }
            String [] arr = users[i].getfFollows();
            for(int j = 0; j < users[i].getfCount(); j++) {
                if(arr[j].equals(name)) {
                continue;
                }
                if(user1.follows(arr[j])) {
                    count++;
                }
            }
          if(maxValue < count) {
            maxValue = count;
            mostRecommendedUserToFollow = users[i];
          }
          count = 0;

        }
        if(mostRecommendedUserToFollow == null) {
            return null;
        }
        return mostRecommendedUserToFollow.getName();
    }

    /** Computes and returns the name of the most popular user in this network: 
     *  The user who appears the most in the follow lists of all the users. */
    public String mostPopularUser() {
        User mostPopularUser = null;
        int maxValue = 0;
        for(int i = 0; i< userCount; i++) {
                if(followeeCount(users[i].getName()) > maxValue) {
                    maxValue = followeeCount(users[i].getName());
                    mostPopularUser = users[i];
                }
            }
        if(mostPopularUser == null) {
            return null;
        }
        return mostPopularUser.getName();
    }

    /** Returns the number of times that the given name appears in the follows lists of all
     *  the users in this network. Note: A name can appear 0 or 1 times in each list. */
    private int followeeCount(String name) {
        int count = 0;
        for(int i = 0; i < userCount; i++) {
            if(users[i].getName().equals(name)) {
                continue;
            }
            String [] arr = users[i].getfFollows();
            for(int j = 0; j < users[i].getfCount(); j++) {
                if(arr[j].equals(name)) {
                    count++;
                    break;
                }
            }
    }
        return count;
}

    // Returns a textual description of all the users in this network, and who they follow.
    public String toString() {
      String s = "Network:";
       int i = 0;
       while(i < userCount) { 
        s += "\n" + users[i];
        i++;
       } 
       return s;
    }
}
