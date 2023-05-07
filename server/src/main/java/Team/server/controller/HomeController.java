package Team.server.controller;

import Team.server.domain.User;
import Team.server.service.UserService;
import Team.server.service.dto.UserDto;
import Team.server.service.dto.UserDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class HomeController {

    private final UserService userService;

    @GetMapping("/register")        // /users/register로 이동할 시
    public String registForm(@ModelAttribute UserDto userDto){
            return "/users/register";
    }

    @PostMapping("/register")
    public String save(@Valid @ModelAttribute UserDto userdto, BindingResult result) throws Exception {
        if(result.hasErrors()){
            System.out.println(result);
            System.out.println("홈 컨트롤러 문제");
            return "/users/register";    //회원가입 정보 이상하면 회원가입 페이지로 다시 리다이렉트
            
        }
        else{
            if(userService.signupCheck(userdto)){
                userService.signup(userdto);
            }
        }                                                                                                                           
        return "redirect:/";        //저장되면 메인 페이지로로

    }

    /*              중복 확인하는 메소드인데 쓰는법을 몰라서 해결 해야될 듯.
    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(@ModelAttribute UserDtoConverter userDtoConverter) {
        int count = userService.studentIdCheck(userDtoConverter.getStudentId());
        return count;
    }


    */

}
