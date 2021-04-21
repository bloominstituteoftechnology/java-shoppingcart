package com.lambdaschool.shoppingcart.controllers;

import com.lambdaschool.shoppingcart.exceptions.ResourceFoundException;
import com.lambdaschool.shoppingcart.exceptions.ResourceNotFoundException;
import com.lambdaschool.shoppingcart.models.*;
import com.lambdaschool.shoppingcart.repository.RoleRepository;
import com.lambdaschool.shoppingcart.repository.UserRepository;
import com.lambdaschool.shoppingcart.services.RoleService;
import com.lambdaschool.shoppingcart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NewUserController {

  @Autowired
  private UserRepository userrepos;

  @Autowired
  private RoleRepository rolerepos;


  @PostMapping(value = "/createnewuser",
      consumes = {"application/json"},
      produces = {"application/json"})
  public ResponseEntity<?> addSelf(
      HttpServletRequest httpServletRequest,
      @Valid
      @RequestBody
          UserMinimum checkuser) throws URISyntaxException {
    if (checkuser.getUsername().isBlank() || checkuser.getPassword().isBlank() ){
      throw new ResourceNotFoundException("Username, and/or password cannot be blank");
    }
    if (userrepos.findByUsername(checkuser.getUsername()) != null) {
      throw new ResourceFoundException("Username already exists!");
    }
    User newuser = new User();
    newuser.setUsername(checkuser.getUsername().toLowerCase());
    newuser.setPassword(checkuser.getPassword());
    newuser.setPrimaryemail(checkuser.getPrimaryemail().toLowerCase());
    newuser.getRoles().clear();
    for (UserRoles ur : newuser.getRoles())
    {
      Role addRole = rolerepos.findByNameIgnoreCase(("USER"));
      newuser.getRoles()
             .add(new UserRoles(newuser,
                                addRole));
    }
    newuser = userrepos.save(newuser);

    HttpHeaders responseHeaders = new HttpHeaders();
    URI newUserURI = ServletUriComponentsBuilder.fromUriString(httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/users/user/{userId}")
                                                .buildAndExpand(newuser.getUserid())
                                                .toUri();
    responseHeaders.setLocation(newUserURI);

    RestTemplate restTemplate = new RestTemplate();
    String requestURI = "http://localhost" + ":" + httpServletRequest.getLocalPort() + "/login";

    List<MediaType> acceptableMediaTypes = new ArrayList<>();
    acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setAccept(acceptableMediaTypes);
    headers.setBasicAuth(System.getenv("OAUTHCLIENTID"),
                         System.getenv("OAUTHCLIENTSECRET"));

    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("grant_type",
            "password");
    map.add("scope",
            "read write trust");
    map.add("username",
            checkuser.getUsername());
    map.add("password",
            checkuser.getPassword());

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,
                                                                         headers);

    String theToken = restTemplate.postForObject(requestURI,
                                                 request,
                                                 String.class);

    return new ResponseEntity<>(theToken,
                                responseHeaders,
                                HttpStatus.CREATED);
  }
}
