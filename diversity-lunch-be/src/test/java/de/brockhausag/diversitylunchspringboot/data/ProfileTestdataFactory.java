package de.brockhausag.diversitylunchspringboot.data;

import com.nimbusds.jose.util.Base64URL;
import de.brockhausag.diversitylunchspringboot.profile.model.*;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.ProfileDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.SneakyThrows;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ProfileTestdataFactory {

    private static final long id = 42;
    private static final String name = "John Patrick-Test";
    private static final String email = "joha.patrick@some.tld";
    private static final int birthYear = 1957;
    private static final Project project = Project.ExampleCompany1;
    private static final Gender gender = Gender.MALE;
    private static final Country originCountry = Country.DEUTSCHLAND;
    private static final Language motherTongue = Language.DEUTSCH;
    private static final Religion religion = Religion.ISLAM;
    private static final Hobby hobby = Hobby.ARCHERY;
    private static final Education education = Education.STUDY;
    private static final WorkExperience workExperience = WorkExperience.HIGH_EXPERIENCE;
    private static final Diet diet = Diet.MEAT;

    public ProfileEntity entity() {
        return this.entityBuilder().build();
    }

    public ProfileEntity.ProfileEntityBuilder entityBuilder() {
        return ProfileEntity.builder()
                .id(id)
                .name(name)
                .email(email)
                .birthYear(birthYear)
                .currentProject(project)
                .gender(gender)
                .originCountry(originCountry)
                .motherTongue(motherTongue)
                .religion(religion)
                .hobby(hobby)
                .education(education)
                .workExperience(workExperience)
                .diet(diet);
    }

    public ProfileEntity createEntity() {
        return this.entityBuilder().id(0).build();
    }

    public ProfileEntity.ProfileEntityBuilder createEntityBuilder() {
        return this.entityBuilder().id(0);
    }

    public CreateProfileDto createDto() {
        return this.createDtoBuilder().build();
    }

    public CreateProfileDto.CreateProfileDtoBuilder createDtoBuilder() {
        return CreateProfileDto.builder()
                .name(name)
                .email(email)
                .birthYear(birthYear)
                .currentProject(project)
                .gender(gender)
                .originCountry(originCountry)
                .motherTongue(motherTongue)
                .religion(religion)
                .hobby(Hobby.ARCHERY)
                .education(education)
                .workExperience(workExperience)
                .diet(diet);
    }

    public ProfileDto dto() {
        return this.dtoBuilder().build();
    }

    public ProfileDto.ProfileDtoBuilder dtoBuilder() {
        return ProfileDto.builder()
                .id(id)
                .name(name)
                .email(email)
                .birthYear(birthYear)
                .currentProject(project)
                .gender(gender)
                .originCountry(originCountry)
                .motherTongue(motherTongue)
                .religion(religion)
                .hobby(hobby)
                .education(education)
                .workExperience(workExperience)
                .diet(diet);
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
                    "unique_name": "%s",
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
                    "unique_name": "%s",
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
    public String getTokenStringFromIdChangedUniqueName(String id) {

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
                    "unique_name": "%s",
                    "given_name": "%s",
                    "family_name": "%s",
                    "exp": %s
                }
                """
                .formatted(id, "", "", Integer.MAX_VALUE)
                .getBytes();

        byte[] payloadManipulate = """
                {
                    "unique_name": "%s",
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
