package de.brockhausag.diversitylunchspringboot.integrationDataFactories;

import com.nimbusds.jose.util.Base64URL;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.*;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.BasicDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.MultiselectDimensionService;
import de.brockhausag.diversitylunchspringboot.dimensions.services.model.WeightedDimensionService;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
@Component
@RequiredArgsConstructor
//@NoArgsConstructor
//@AllArgsConstructor
public class ProfileTestdataFactory {

    @Test
    void testFalse(){
        assertFalse(true);
    }
    private final BasicDimensionService basicDimensionService;
    private final MultiselectDimensionService multiselectDimensionService;
    private final WeightedDimensionService weightedDimensionService;

    public ProfileEntity createNewMaxProfile() {
        Map<BasicDimension, BasicDimensionSelectableOption> selectedBasicValues = new HashMap<>();
        basicDimensionService.getAllDimensions().forEach(
                basicDimension -> {
                    BasicDimensionSelectableOption option = basicDimensionService.getSelectableOptions(basicDimension).get(0);
                    selectedBasicValues.put(basicDimension, option);
                }
        );

        Map<WeightedDimension, WeightedDimensionSelectableOption> selectedWeightedValues = new HashMap<>();
        weightedDimensionService.getAllDimensions().forEach(
                weightedDimension -> {
                    WeightedDimensionSelectableOption option = weightedDimensionService.getSelectableOptions(weightedDimension).get(0);
                    selectedWeightedValues.put(weightedDimension, option);
                }
        );

        Map<MultiselectDimension, ProfileEntitySelectedMultiselectValue> selectedMultiselectValues = new HashMap<>();
        multiselectDimensionService.getAllDimensions().forEach(
                multiselectDimension -> {
                    List<MultiselectDimensionSelectableOption> options = multiselectDimensionService.getSelectableOptions(multiselectDimension).subList(0, 2);
                    ProfileEntitySelectedMultiselectValue profileOptions = new ProfileEntitySelectedMultiselectValue();
                    profileOptions.setSelectedOptions(Set.copyOf(options));
                    selectedMultiselectValues.put(multiselectDimension, profileOptions);
                }
        );

        return ProfileEntity.builder()
                .id(1L)
                .name("Max Mustermann")
                .email("Max@Mustermann.de")
                .birthYear(1996)
                .wasChangedByAdmin(false)
                .selectedBasicValues(selectedBasicValues)
                .selectedWeightedValues(selectedWeightedValues)
                .selectedMultiselectValues(selectedMultiselectValues)
                .build();
    }

//    public ProfileEntity createNewErikaProfile() {
//        final Long id = 2L;
//        final String name = "Erika";
//        final String email = "Erika@Mustermann.de";
//        final int birthyear = 1976;
//        final CountryEntity originCountry = countryService.getAllEntities().get(1);
//        final DietEntity diet = dietService.getAllEntities().get(1);
//        final EducationEntity education = educationService.getAllEntities().get(1);
//        final GenderEntity gender = genderService.getAllEntities().get(1);
//        final LanguageEntity motherTongue = languageService.getAllEntities().get(1);
//        final ProjectEntity project = projectService.getAllEntities().get(1);
//        final ReligionEntity religion = religionService.getAllEntities().get(1);
//        final WorkExperienceEntity workExperience = workExperienceService.getAllEntities().get(1);
//        final List<HobbyEntity> hobby = hobbyService.getAllEntities().subList(0, 3);
//        final SexualOrientationEntity sexualOrientation = sexualOrientationService.getAllEntities().get(1);
//        final SocialBackgroundEntity socialBackground = socialBackgroundService.getAllEntities().get(1);
//        final SocialBackgroundDiscriminationEntity socialBackgroundDiscrimination = socialBackgroundDiscriminationService.getAllEntities().get(1);
//
//        return new ProfileEntity(id,
//                name,
//                email,
//                birthyear,
//                originCountry,
//                diet,
//                education,
//                gender,
//                motherTongue,
//                project,
//                religion,
//                workExperience,
//                hobby,
//                sexualOrientation,
//                socialBackground,
//                socialBackgroundDiscrimination,
//                false);
//    }

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
