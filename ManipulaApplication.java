package com.example.manipula;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONException;
import org.json.JSONObject;

@SpringBootApplication
@RestController
public class ManipulaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManipulaApplication.class, args);
	}
	
	@PostMapping("/verificar_palindromo")

    public String verificarPalindromo(@RequestBody String requestBody) {

        JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(requestBody);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        String inputString = null;
		try {
			inputString = jsonObject.getString("texto");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       

        boolean palindrome = isPalindrome(inputString);

        JSONObject charCount = countCharacters(inputString);       

        JSONObject response = new JSONObject();

        try {
			response.put("palindromo", palindrome);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			response.put("ocorrencias_caracteres", charCount);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       

        return response.toString();

    }

    private boolean isPalindrome(String s) {

        // Remove espaços em branco e transforma em minúsculas

        s = s.replaceAll("\\s+", "").toLowerCase();

        int left = 0;

        int right = s.length() - 1;       

        while (left < right) {

            if (s.charAt(left) != s.charAt(right)) {

                return false;

            }

            left++;

            right--;

        }

        return true;
    } 

    private JSONObject countCharacters(String s) {

        JSONObject charCount = new JSONObject();

        for (char c : s.toCharArray()) {

            String character = String.valueOf(c);

            try {
				charCount.put(character, charCount.optInt(character, 0) + 1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }

        return charCount;
    }
}