package br.uaijug.tutorial.endpoints;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

public class Endpoints {

	/*
	private static final Log log = LogFactory.getLog(Endpoints.class);
	
	//Properties for encryptation
	private final String IV = "F27D5C9927726BCEFE7510B1BDD3D137";
	private final String SALT = "3FF2EC019C627B945225DEBAD71A01B6985FE84C95A70EB132882F88C0A59A55";
	private final int KEY_SIZE = 128;
	private final int ITERATION_COUNT = 10;
	private final String PASSPHRASE = "i wanna be sedated";
	
	@Autowired
	@Value("${post.page.size}") 
	private Integer postPageSize;
	
	@Autowired
	@Value("${app.version}") 
	private String appVersion;
	
	@Autowired
	private GenericDao dao;
	
	@Autowired
	private MemcachedClient cache;
	
	private final Queue<User> updateUserQueue = new ConcurrentLinkedQueue<User>();
	
	@RequestMapping(value = "/register", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseBody
	public HttpEntity<String> registerDevice(
			@RequestParam(value = "regId", required = true) String value,
			@RequestParam(value = "platform", required = false, defaultValue = "android") String type) {
		
		User user = dao.createOrUpdateUser(value, type);
		
		ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
			json = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
        log.debug("Antes: " + json);
        json = encrypt(json);
		log.debug(json);
		
		log.info("User " + value + " | " + type + " criado.");
		return new ResponseEntity<>(json, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/posts", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseBody
	public HttpEntity<String> listPosts(@RequestParam(value = "appVersion", required = false, defaultValue = "1.0") String appVersion,
			@RequestParam(value = "sources", required = false, defaultValue = "") String sources,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber) {
		
		String json = "";
		HttpStatus status = HttpStatus.OK;
		if(!this.appVersion.equals(appVersion)) {
	        status = HttpStatus.UPGRADE_REQUIRED;
		} else {
			List<Post> posts = dao.getPostsByFilter(pageNumber, postPageSize);
			
			ObjectMapper mapper = new ObjectMapper();
	        json = "";
	        try {
				json = mapper.writeValueAsString(posts);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
	        log.debug("Antes: " + json);
	        json = encrypt(json);
			log.debug(json);
		}
		return new ResponseEntity<>(json, status);
	}

	
	@RequestMapping(value = "/newPosts", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseBody
	public HttpEntity<String> newPosts(@RequestParam(value = "appVersion", required = false, defaultValue = "1.0") String appVersion,
			@RequestParam(value = "sourceId", required = false, defaultValue = "0") Integer sourceId,
			@RequestParam(value = "words", required = false, defaultValue = "") String words,
			@RequestParam(value = "firstPostNumber", required = false, defaultValue = "0") Long firstPostNumber) {
		
		String json = "";
		HttpStatus status = HttpStatus.OK;
		if(!this.appVersion.equals(appVersion)) {
	        status = HttpStatus.UPGRADE_REQUIRED;
		} else {
			List<Post> posts = null;
			if(sourceId == 0) {
				json = (String) cache.get(sourceId+"-"+firstPostNumber);
				if(json != null) {
					log.info("FROM MEMCACHED! | sourceId: " + sourceId + " | fistValue: " + firstPostNumber + " | words: " + words);
					return new ResponseEntity<>(json, status);
				} else {
					posts = dao.getNewPostsByFilter(firstPostNumber, postPageSize);
				}
			} else if(sourceId == -1) {
				posts = dao.getNewPostsByWords(firstPostNumber, words, postPageSize);
			} else {
				json = (String) cache.get(sourceId+"-"+firstPostNumber);
				if(json != null) {
					log.info("FROM MEMCACHED! | sourceId: " + sourceId + " | fistValue: " + firstPostNumber + " | words: " + words);
					return new ResponseEntity<>(json, status);
				} else {
					posts = dao.getNewPostsByFilter(firstPostNumber, sourceId, postPageSize);
				}
			}
			
			ObjectMapper mapper = new ObjectMapper();
	        json = "";
	        try {
				json = mapper.writeValueAsString(posts);
				log.info(posts.size() + " posts retornados | sourceId: " + sourceId + " | fistValue: " + firstPostNumber + " | words: " + words);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
	        
	        log.debug("Antes: " + json);
	        json = encrypt(json);
			log.debug(json);
			if(sourceId != -1) {
				cache.set(sourceId+"-"+firstPostNumber, 180, json);
			}
		}
		return new ResponseEntity<>(json, status);
	}

	@RequestMapping(value = "/morePosts", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseBody
	public HttpEntity<String> morePosts(@RequestParam(value = "appVersion", required = false, defaultValue = "1.0") String appVersion,
			@RequestParam(value = "sourceId", required = false, defaultValue = "0") Integer sourceId,
			@RequestParam(value = "words", required = false, defaultValue = "") String words,
			@RequestParam(value = "lastPostNumber", required = false, defaultValue = "0") Long lastPostNumber) {
		
		String json = "";
		HttpStatus status = HttpStatus.OK;
		if(!this.appVersion.equals(appVersion)) {
	        status = HttpStatus.UPGRADE_REQUIRED;
		} else {
			List<Post> posts = null;
			
			if(sourceId == 0) {
				posts = dao.getMorePostsByFilter(lastPostNumber, postPageSize);
			} else if(sourceId == -1) {
				posts = dao.getMorePostsByWords(lastPostNumber, words, postPageSize);
			} else {
				posts = dao.getMorePostsByFilter(lastPostNumber, sourceId, postPageSize);
			}

			
			ObjectMapper mapper = new ObjectMapper();
	        json = "";
	        try {
				json = mapper.writeValueAsString(posts);
				log.info(posts.size() + " posts retornados | sourceId: " + sourceId + " | lastValue: " + lastPostNumber + " | words: " + words);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
	        log.debug("Antes: " + json);
	        json = encrypt(json);
			log.debug(json);
		}
		return new ResponseEntity<>(json, status);
	}

	
	
	@RequestMapping(value = "/sources", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseBody
	public HttpEntity<String> listSources() {
		
		String json = "";
		HttpStatus status = HttpStatus.OK;
		List<Source> source = dao.getActiveSources();
		
		ObjectMapper mapper = new ObjectMapper();
        json = "";
        try {
			json = mapper.writeValueAsString(source);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
        log.debug("Antes: " + json);
        json = encrypt(json);
		log.debug(json);

		return new ResponseEntity<>(json, status);
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseBody
	public HttpEntity<String> getPostById(
			@RequestParam(value = "id", required = true) Long id) {
		
		Post post = dao.getPostById(id);
		
		ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
			json = mapper.writeValueAsString(post);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
        log.debug("Antes: " + json);
        json = encrypt(json);
		log.debug(json);
		
		return new ResponseEntity<>(json, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getuserconfig", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseBody
	public HttpEntity<String> getUserById(
			@RequestParam(value = "id", required = true) String id) {
		
		User user = dao.getUserById(id);
		
		ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
			json = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
        log.debug("Antes: " + json);
        json = encrypt(json);
		log.debug(json);
		
		return new ResponseEntity<>(json, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveuserconfig", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseBody
	public HttpEntity<String> saveUserConfig(
			@RequestParam(value = "payload", required = true) String payload) {
		
		String json = decrypt(payload);
		ObjectMapper mapper = new ObjectMapper();
        try {
        	User user = mapper.readValue(json, User.class);
        	this.updateUserQueue.add(user);
        	json = mapper.writeValueAsString(user);
        	
		} catch (IOException e) {
			e.printStackTrace();
		}
        json = encrypt(json);
        
		return new ResponseEntity<>(json, HttpStatus.OK);
	}
	
	
	private String encrypt(String input) {
		AesUtil util = new AesUtil(KEY_SIZE, ITERATION_COUNT);
        return util.encrypt(SALT, IV, PASSPHRASE, input);
	}

	private String decrypt(String input) {
		AesUtil util = new AesUtil(KEY_SIZE, ITERATION_COUNT);
        return util.decrypt(SALT, IV, PASSPHRASE, input);
	}

	
	@Scheduled(fixedDelay=5000)
	public void processQueues() {
		for (User user : this.updateUserQueue) {
			try {
				dao.updateUser(user);
				log.info("User " + user.getRegId() + " atualizado.");
			} catch (Exception e) {
				//e.printStackTrace();
				log.error("User " + user.getRegId() + " não atualizado.",e);
			}
			this.updateUserQueue.remove(user);
		}
	}
	*/
}
