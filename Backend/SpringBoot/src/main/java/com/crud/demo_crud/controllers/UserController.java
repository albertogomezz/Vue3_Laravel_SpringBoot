package com.crud.demo_crud.controllers;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import com.crud.demo_crud.model.BlacklistToken;
import com.crud.demo_crud.model.User;
import com.crud.demo_crud.model.UserAndToken;
// import com.crud.demo_crud.repository.BlacklistTokenRepository;
import com.crud.demo_crud.repository.PistaRepository;
// import com.crud.demo_crud.repository.ReservationRepository;
import com.crud.demo_crud.repository.UserRepository;
import com.crud.demo_crud.security.jwt.JwtUtils;
import com.crud.demo_crud.security.jwt.AuthTokenFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.crud.demo_crud.model.Pista;
// import com.crud.demo_crud.model.Reservation;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository UserRepository;

    // @Autowired
    // private BlacklistTokenRepository BlacklistTokenRepository;

    @Autowired
    private AuthTokenFilter authTokenFilter;
    
    @Autowired
    private PistaRepository PistaRepository;

    // @Autowired
    // private ReservationRepository ReservationRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

        @PostMapping("/login")
    public ResponseEntity<UserAndToken> loginUser(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            User user_ = UserRepository.findByUsername(user.getUsername()).get();
            UserAndToken userToken = new UserAndToken(jwt, user_);

            return new ResponseEntity<>(userToken, HttpStatus.OK);

        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // public Integer registerUser(@RequestBody User user) {
        try {

            if (UserRepository.existsByUsername(user.getUsername()) > 0) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else if (UserRepository.existsByUsername(user.getEmail()) > 0) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                user.setType("client");
                user.setIs_active(true);
                user.setPassword(encoder.encode(user.getPassword()));
                UserRepository.save(user);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        @GetMapping("/profile")
    public ResponseEntity<User> profile() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            User user = UserRepository.findByUsername(userDetails.getUsername()).get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() { // HttpServletRequest request
        // try {
            // String token = authTokenFilter.parseJwt(request);
            // if (BlacklistTokenRepository.TokenExist(token) == 0) {
            //     BlacklistToken blacklistToken = new BlacklistToken();
            //     blacklistToken.setToken(token);
            //     BlacklistTokenRepository.save(blacklistToken);
            // }
            return new ResponseEntity<>(HttpStatus.OK);

        // } catch (Exception e) {
        //     System.err.println(e);
        //     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        // }
    }







    

    // @GetMapping("/listReservation")
    // public ResponseEntity<List<Pista>> listReservation() {
    //     try {
    //         UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
    //                 .getPrincipal();
    //         User user = UserRepository.findByUsername(userDetails.getUsername()).get();

    //         if (user == null) {
    //             return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    //         }
    //         List<Pista> mesas = new ArrayList<Pista>();
    //         PistaRepository.showUserReservation(user.getId()).forEach(mesas::add);
    //         ;
    //         return new ResponseEntity<>(mesas, HttpStatus.OK);

    //     } catch (Exception e) {
    //         System.out.println(e);
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    // @GetMapping("/listReservation/{mesa_id}")
    // public ResponseEntity<List<Reservation>> getReservationsOfMesa(@PathVariable(required = true) Long mesa_id) {
    //     try {
    //         UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
    //                 .getPrincipal();
    //         User user = UserRepository.findByUsername(userDetails.getUsername()).get();

    //         if (user == null) {
    //             return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    //         }
    //         List<Reservation> mesas = new ArrayList<Reservation>();
    //         ReservationRepository.showReservationsOfMesa(user.getId(), mesa_id).forEach(mesas::add);
    //         return new ResponseEntity<>(mesas, HttpStatus.OK);

    //     } catch (Exception e) {
    //         System.out.println(e);
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }// get one


}
