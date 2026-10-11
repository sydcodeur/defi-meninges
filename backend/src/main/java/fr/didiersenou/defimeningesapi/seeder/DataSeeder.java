package fr.didiersenou.defimeningesapi.seeder;

import fr.didiersenou.defimeningesapi.entity.Answer;
import fr.didiersenou.defimeningesapi.entity.Category;
import fr.didiersenou.defimeningesapi.entity.Question;
import fr.didiersenou.defimeningesapi.entity.User;
import fr.didiersenou.defimeningesapi.enums.Role;
import fr.didiersenou.defimeningesapi.repository.AnswerRepository;
import fr.didiersenou.defimeningesapi.repository.CategoryRepository;
import fr.didiersenou.defimeningesapi.repository.QuestionRepository;
import fr.didiersenou.defimeningesapi.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SeedTestUserProperties testUserProperties;

    public DataSeeder(CategoryRepository categoryRepository,
            QuestionRepository questionRepository,
            AnswerRepository answerRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            SeedTestUserProperties testUserProperties) {
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.testUserProperties = testUserProperties;
    }

    @Override
    @Transactional
    public void run(String... args) {
        seedTestUser();
        seedCategories();
    }

    private void seedCategories() {
        if (categoryRepository.count() > 0) {
            log.info("Categories already seeded, skipping.");
            return;
        }

        seedGeneralKnowledgeCategory();
        seedTechAndDevCategory();

        log.info("Seeded 4 categories, 8 questions and 32 answers (EN + FR).");
    }

    private void seedGeneralKnowledgeCategory() {
        Category generalKnowledgeEn = createAndSaveCategory("General Knowledge", "en", 15);

        Question q1En = createAndSaveQuestion(
                "What is the capital of Australia?",
                "en",
                1,
                15,
                generalKnowledgeEn);
        answerRepository.saveAll(List.of(
                createAnswer("Sydney", false, "en", q1En),
                createAnswer("Melbourne", false, "en", q1En),
                createAnswer("Canberra", true, "en", q1En),
                createAnswer("Perth", false, "en", q1En)));

        Question q2En = createAndSaveQuestion(
                "How many continents are there on Earth?",
                "en",
                1,
                15,
                generalKnowledgeEn);
        answerRepository.saveAll(List.of(
                createAnswer("5", false, "en", q2En),
                createAnswer("6", false, "en", q2En),
                createAnswer("7", true, "en", q2En),
                createAnswer("8", false, "en", q2En)));

        Category generalKnowledgeFr = createAndSaveCategory("Culture Générale", "fr", 15);

        Question q1Fr = createAndSaveQuestion(
                "Quelle est la capitale de l'Australie ?",
                "fr",
                1,
                15,
                generalKnowledgeFr);
        answerRepository.saveAll(List.of(
                createAnswer("Sydney", false, "fr", q1Fr),
                createAnswer("Melbourne", false, "fr", q1Fr),
                createAnswer("Canberra", true, "fr", q1Fr),
                createAnswer("Perth", false, "fr", q1Fr)));

        Question q2Fr = createAndSaveQuestion(
                "Combien y a-t-il de continents sur Terre ?",
                "fr",
                1,
                15,
                generalKnowledgeFr);
        answerRepository.saveAll(List.of(
                createAnswer("5", false, "fr", q2Fr),
                createAnswer("6", false, "fr", q2Fr),
                createAnswer("7", true, "fr", q2Fr),
                createAnswer("8", false, "fr", q2Fr)));
    }

    private void seedTechAndDevCategory() {
        Category techAndDevEn = createAndSaveCategory("Tech & Dev", "en", 15);

        Question q1En = createAndSaveQuestion(
                "Who invented the World Wide Web?",
                "en",
                2,
                15,
                techAndDevEn);
        answerRepository.saveAll(List.of(
                createAnswer("Bill Gates", false, "en", q1En),
                createAnswer("Tim Berners-Lee", true, "en", q1En),
                createAnswer("Steve Jobs", false, "en", q1En),
                createAnswer("Linus Torvalds", false, "en", q1En)));

        Question q2En = createAndSaveQuestion(
                "Which programming language is primarily used for native Android development?",
                "en",
                2,
                15,
                techAndDevEn);
        answerRepository.saveAll(List.of(
                createAnswer("Swift", false, "en", q2En),
                createAnswer("Python", false, "en", q2En),
                createAnswer("Kotlin", true, "en", q2En),
                createAnswer("JavaScript", false, "en", q2En)));

        Category techAndDevFr = createAndSaveCategory("Tech & Dev", "fr", 15);

        Question q1Fr = createAndSaveQuestion(
                "Qui a inventé le World Wide Web ?",
                "fr",
                2,
                15,
                techAndDevFr);
        answerRepository.saveAll(List.of(
                createAnswer("Bill Gates", false, "fr", q1Fr),
                createAnswer("Tim Berners-Lee", true, "fr", q1Fr),
                createAnswer("Steve Jobs", false, "fr", q1Fr),
                createAnswer("Linus Torvalds", false, "fr", q1Fr)));

        Question q2Fr = createAndSaveQuestion(
                "Quel langage de programmation est principalement utilisé pour développer des applications Android natives ?",
                "fr",
                2,
                15,
                techAndDevFr);
        answerRepository.saveAll(List.of(
                createAnswer("Swift", false, "fr", q2Fr),
                createAnswer("Python", false, "fr", q2Fr),
                createAnswer("Kotlin", true, "fr", q2Fr),
                createAnswer("JavaScript", false, "fr", q2Fr)));
    }

    private Category createAndSaveCategory(String name, String locale, Integer defaultTimeLimit) {
        Category category = new Category();
        category.setName(name);
        category.setLocale(locale);
        category.setDefaultTimeLimit(defaultTimeLimit);
        return categoryRepository.save(category);
    }

    private Question createAndSaveQuestion(String content, String locale, Integer difficultyLevel,
            Integer timeLimit, Category category) {
        Question question = new Question();
        question.setContent(content);
        question.setLocale(locale);
        question.setDifficultyLevel(difficultyLevel);
        question.setTimeLimit(timeLimit);
        question.setCategory(category);
        return questionRepository.save(question);
    }

    private Answer createAnswer(String content, boolean isCorrect, String locale, Question question) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCorrect(isCorrect);
        answer.setLocale(locale);
        answer.setQuestion(question);
        return answer;
    }

    private void seedTestUser() {
        if (!testUserProperties.isConfigured()) {
            log.warn("Test user seeding skipped: app.seed.test-user.* properties are not set.");
            return;
        }

        if (userRepository.existsByEmail(testUserProperties.email())
                || userRepository.existsByUsername(testUserProperties.username())) {
            return;
        }

        User testUser = new User();
        testUser.setUsername(testUserProperties.username());
        testUser.setEmail(testUserProperties.email());
        testUser.setPasswordHash(passwordEncoder.encode(testUserProperties.password()));
        testUser.setRole(Role.PLAYER);
        testUser.setTotalScore(0);
        userRepository.save(testUser);

        log.info("Seeded test user '{}'.", testUserProperties.username());
    }
}