package de.brockhausag.diversitylunchspringboot.integrationDataFactories;

import com.nimbusds.jose.util.Base64URL;
import de.brockhausag.diversitylunchspringboot.profile.logic.*;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProfileTestdataFactory {

    private final CountryService countryService;
    private final DietService dietService;
    private final EducationService educationService;
    private final SexualOrientationService sexualOrientationService;
    private final GenderService genderService;
    private final HobbyService hobbyService;
    private final LanguageService languageService;
    private final ProjectService projectService;
    private final ReligionService religionService;
    private final WorkExperienceService workExperienceService;
    private final SocialBackgroundService socialBackgroundService;
    private final SocialBackgroundDiscriminationService socialBackgroundDiscriminationService;


    public ProfileEntity createNewMaxProfile() {
        final Long id = 1L;
        final String name = "Max";
        final String email = "Max@Mustermann.de";
        final int birthyear = 1996;
        final CountryEntity originCountry = countryService.getAllEntities().get(0);
        final DietEntity diet = dietService.getAllEntities().get(0);
        final EducationEntity education = educationService.getAllEntities().get(0);
        final GenderEntity gender = genderService.getAllEntities().get(0);
        final LanguageEntity motherTongue = languageService.getAllEntities().get(0);
        final ProjectEntity project = projectService.getAllEntities().get(0);
        final ReligionEntity religion = religionService.getAllEntities().get(0);
        final WorkExperienceEntity workExperience = workExperienceService.getAllEntities().get(0);
        final List<HobbyEntity> hobby = hobbyService.getAllEntities().subList(0, 3);
        final SexualOrientationEntity sexualOrientation = sexualOrientationService.getAllEntities().get(0);
        final SocialBackgroundEntity socialBackground = socialBackgroundService.getAllEntities().get(0);
        final SocialBackgroundDiscriminationEntity socialBackgroundDiscrimination = socialBackgroundDiscriminationService.getAllEntities().get(0);

        return new ProfileEntity(id,
                name,
                email,
                birthyear,
                originCountry,
                diet,
                education,
                gender,
                motherTongue,
                project,
                religion,
                workExperience,
                hobby,
                sexualOrientation,
                socialBackground,
                socialBackgroundDiscrimination);
    }

    public ProfileEntity createNewErikaProfile() {
        final Long id = 2L;
        final String name = "Erika";
        final String email = "Erika@Mustermann.de";
        final int birthyear = 1976;
        final CountryEntity originCountry = countryService.getAllEntities().get(1);
        final DietEntity diet = dietService.getAllEntities().get(1);
        final EducationEntity education = educationService.getAllEntities().get(1);
        final GenderEntity gender = genderService.getAllEntities().get(1);
        final LanguageEntity motherTongue = languageService.getAllEntities().get(1);
        final ProjectEntity project = projectService.getAllEntities().get(1);
        final ReligionEntity religion = religionService.getAllEntities().get(1);
        final WorkExperienceEntity workExperience = workExperienceService.getAllEntities().get(1);
        final List<HobbyEntity> hobby = hobbyService.getAllEntities().subList(0, 3);
        final SexualOrientationEntity sexualOrientation = sexualOrientationService.getAllEntities().get(1);
        final SocialBackgroundEntity socialBackground = socialBackgroundService.getAllEntities().get(1);
        final SocialBackgroundDiscriminationEntity socialBackgroundDiscrimination = socialBackgroundDiscriminationService.getAllEntities().get(1);

        return new ProfileEntity(id,
                name,
                email,
                birthyear,
                originCountry,
                diet,
                education,
                gender,
                motherTongue,
                project,
                religion,
                workExperience,
                hobby,
                sexualOrientation,
                socialBackground,
                socialBackgroundDiscrimination);
    }

    @SneakyThrows
    public String getTokenStringFromId(String id) {

        byte[] key = "SomeSecretWeDontCheckItAnywayToS".getBytes();
        byte[] header = """
                {
                    "alg": "HS256",
                    "typ": "JWT"
                }
                """
                .getBytes();
        byte[] payload = """
                {
                    "oid": "%s",
                    "given_name": "%s",
                    "family_name": "%s",
                    "exp": %s
                }
                """
                .formatted(id, "", "", Integer.MAX_VALUE)
                .getBytes();


        String encoded = Base64URL.encode(header) + "." + Base64URL.encode(payload);

        try {
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret = new SecretKeySpec(key, "HmacSHA256");
            sha256Hmac.init(secret);
            String hash = Base64URL.encode(sha256Hmac.doFinal(encoded.getBytes())).toString();

            return encoded + "." + hash;
        } catch (NoSuchAlgorithmException exception) {
            throw new NoSuchAlgorithmException("The Hmac algorithm was changed and is not longer valid.", exception);
        } catch (InvalidKeyException exception) {
            throw new InvalidKeyException("The key was changed and is not longer valid.", exception);
        }
    }

    @SneakyThrows
    public String getTokenStringFromIdWrongKey(String id) {

        byte[] key = "SomeSecretWeDontCheckItAnywayTOS".getBytes();
        byte[] header = """
                {
                    "alg": "HS256",
                    "typ": "JWT"
                }
                """
                .getBytes();
        byte[] payload = """
                {
                    "oid": "%s",
                    "given_name": "%s",
                    "family_name": "%s",
                    "exp": %s
                }
                """
                .formatted(id, "", "", Integer.MAX_VALUE)
                .getBytes();


        String encoded = Base64URL.encode(header) + "." + Base64URL.encode(payload);

        try {
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret = new SecretKeySpec(key, "HmacSHA256");
            sha256Hmac.init(secret);
            String hash = Base64URL.encode(sha256Hmac.doFinal(encoded.getBytes())).toString();

            return encoded + "." + hash;
        } catch (NoSuchAlgorithmException exception) {
            throw new NoSuchAlgorithmException("The Hmac algorithm was changed and is not longer valid.", exception);
        } catch (InvalidKeyException exception) {
            throw new InvalidKeyException("The key was changed and is not longer valid.", exception);
        }
    }

    @SneakyThrows
    public String getTokenStringFromIdChangedOid(String id) {

        byte[] key = "SomeSecretWeDontCheckItAnywayToS".getBytes();
        byte[] header = """
                {
                    "alg": "HS256",
                    "typ": "JWT"
                }
                """
                .getBytes();
        byte[] payload = """
                {
                    "oid": "%s",
                    "given_name": "%s",
                    "family_name": "%s",
                    "exp": %s
                }
                """
                .formatted(id, "", "", Integer.MAX_VALUE)
                .getBytes();

        byte[] payloadManipulate = """
                {
                    "oid": "%s",
                    "given_name": "%s",
                    "family_name": "%s",
                    "exp": %s
                }
                """
                .formatted("manipulate", "", "", Integer.MAX_VALUE)
                .getBytes();

        String encoded = Base64URL.encode(header) + "." + Base64URL.encode(payload);
        String encodedManipulated = Base64URL.encode(header) + "." + Base64URL.encode(payloadManipulate);

        try {
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret = new SecretKeySpec(key, "HmacSHA256");
            sha256Hmac.init(secret);
            String hash = Base64URL.encode(sha256Hmac.doFinal(encoded.getBytes())).toString();

            return encodedManipulated + "." + hash;
        } catch (NoSuchAlgorithmException exception) {
            throw new NoSuchAlgorithmException("The Hmac algorithm was changed and is not longer valid.", exception);
        } catch (InvalidKeyException exception) {
            throw new InvalidKeyException("The key was changed and is not longer valid.", exception);
        }
    }
}
