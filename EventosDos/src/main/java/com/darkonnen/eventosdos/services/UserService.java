package com.darkonnen.eventosdos.services;

@Service
public class UserService {
	private final UserRepository userRepo;
	private final EventRepository eventRepo;
	private final UserEventRepository userEventRepo;
	private final MessageRepository messageRepo;
	
	public UserService(UserRepository userRepo, EventRepository eventRepo, UserEventRepository userEventRepo, MessageRepository messageRepo) {
		this.userRepo = userRepo;
		this.eventRepo = eventRepo;
		this.userEventRepo = userEventRepo;
		this.messageRepo = messageRepo;
	}
	
	public void deleteEvent(Long id) {
		eventRepo.deleteById(id);
	}
	
    public List<Event> allEvents() {
    	return (List<Event>) eventRepo.findAll();
    }
	
	public Event addEvent(Event event) {
		return eventRepo.save(event);
	}
	
	public Message addMessage(Message message) {
		return messageRepo.save(message);
	}
	
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepo.save(user);
    }
    
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    
    public User findUserById(Long id) {
    	Optional<User> u = userRepo.findById(id);
    	if(u.isPresent()) {
            return u.get();
    	}
    	else {
    	    return null;
    	}
    }
    
    public Event findEventById(Long id) {
    	Optional<Event> e = eventRepo.findById(id);
    	if(e.isPresent()) {
            return e.get();
    	}
    	else {
    	    return null;
    	}
    }
    
    public void updateEvent(Event event) {
        eventRepo.save(event);
    }
    
    public void updateUser(User user) {
        userRepo.save(user);
    }
    
    public void updateMessage(Message message) {
    	messageRepo.save(message);
    }
    
    public void newMessage(Message message) {
        messageRepo.save(message);
    }
    
    //Authentication
    public boolean authenticateUser(String email, String password) {
        User user = userRepo.findByEmail(email);
        if(user == null) {
            return false;
        }
        else {
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            }
            else {
                return false;
            }
        }
    }
    
    public boolean duplicateUser(String email) {
        User user = userRepo.findByEmail(email);
        if(user == null) {
            return false;
        }
        else {
        	return true;
        }
    }
}