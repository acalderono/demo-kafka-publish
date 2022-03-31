package com.kafka.publish.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.kafka.publish.model.User;


@RestController
@RequestMapping("kafka")
public class UserResource {

	@Value("${topic}")
	String topic;
	
	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
		
	@GetMapping("/publish/{name}")
	public String post(@PathVariable("name") final String name) {
		kafkaTemplate.send(topic, new User(name, "Consultor", 12000L));
		
		return "Publicado con exito";
	}
	
}