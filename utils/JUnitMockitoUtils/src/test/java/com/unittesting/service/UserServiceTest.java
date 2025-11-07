package com.unittesting.service;

import com.unittesting.data.UsersRepository;
import com.unittesting.model.Message;
import com.unittesting.model.User;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
* JUnit v5.8.2 (JUnit 5 built-in Assertions):
import org.junit.jupiter.api

- Annotations/JUnit Lifecycle Methods:
@BeforeAll: runs before running the Test class.

@BeforeEach: runs before running each test.

@Test

@AfterEach: runs after running each test.

@AfterAll: runs before running the Test class.

@Disabled: disable test
    @Disabled("TODO: Still need to work on it")

@DisplayName: show name for test

@TestInfo

@TestTemplate

@Timeout

- Assertions and assumptions (Assertions.class):

assertAll: testing multiple assertions: JUnit

    e.g.:
    Address address = new Address();
    assertAll("address name",
        () -> assertEquals("John", address.getFirstName()),
        () -> assertEquals("User", address.getLastName())
    );
    output:
    org.opentest4j.MultipleFailuresError: address name (2 failures)
    expected: <John> but was: <null>
    expected: <User> but was: <null>

assertEquals
    assertEquals(Object expected, Object actual)

    e.g.:
    assertEquals(4, calculator.multiply(2, 2),"optional failure message");

assertNotEquals

assertSame
    assertSame(Object expected, Object actual)

assertNotSame

assertTrue
    assertTrue(boolean condition)

    e.g.:
    assertTrue('a' < 'b', () → "optional failure message");

assertFalse
    assertFalse(boolean condition)

    e.g.:
    assertFalse('a' > 'b', () → "optional failure message");

assertNull
    assertNull(Object obj)

    e.g.:
    assertNull(yourObject, "optional failure message");

assertNotNull
    assertNotNull(Object obj)

    e.g.:
    assertNotNull(yourObject, "optional failure message");

fail: intentionally fails test method without checking for any conditions.
    fails a test throwing an AssertionError unconditionally.

    e.g.:
    fail("No implementation provided")

    Using Fail Assertion in JUnit
    https://www.baeldung.com/junit-fail

assertThrows
    e.g.:
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> user.setAge("23"));
    assertEquals("Age must be an Integer.", exception.getMessage());

assertDoesNotThrow

assertTimeout: define Timeout in Test

e.g.:
interface Demo {
    int getInt();
    Integer getInteger();
    double getDouble();
    boolean getBoolean();
    String getObject();
    Collection<String> getCollection();
    String[] getArray();
    Stream<?> getStream();
    Optional<?> getOptional();
}

Demo demo = mock(Demo.class);

assertEquals(0, demo.getInt());
assertEquals(0, demo.getInteger().intValue());
assertEquals(0d, demo.getDouble(), 0d);
assertFalse(demo.getBoolean());
assertNull(demo.getObject());
assertEquals(Collections.emptyList(), demo.getCollection());
assertNull(demo.getArray());
assertEquals(0L, demo.getStream().count());
assertFalse(demo.getOptional().isPresent());

*************************************************

* Assertions with AssertJ library (contains Fluent API):

is more readable than JUnit assertions.

assertThat
	e.g.:
	assertThat(commentService.containsSwearWords("This is a comment")).isFalse();
   
	instead of:
    assertFalse(commentService.containsSwearWords("This is a comment"));

	e.g.:
	User savedUser = userRepository.save(user);
    assertThat(savedUser).usingRecursiveComparison().ignoringFields("userId").isEqualTo(user);

assertThatThrownBy: when throw an Exception
	e.g.:
	assertThatThrownBy(() -> {
        commentService.containsSwearWords("This is a shitty comment");
    }).isInstanceOf(SpringRedditException.class)
            .hasMessage("Comments contains unacceptable language");
			
	instead of:
	CommentService commentService = new CommentService(null, null, null, null, null, null, null);
		SpringRedditException exception = assertThrows(SpringRedditException.class, () -> {
			commentService.containsSwearWords("This is shitty comment");
		});
		assertTrue(exception.getMessage().contains("Comments contains unacceptable language"));
		
*************************************************

* Mockito v4.5.1
package org.mockito
import static org.mockito.Mockito.

- Annotations:

@BeforeClass: called one before all test cases.

@AfterClass: called only one after all test cases.

@Before: called before each test case. Used for initialization.

    e.g.:
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

@After: called after each test case.

@Mock: mock a class or interface.
    Mock: a created object that simulates a subsystem (methods).

    e.g.:
    @Mock
    private UserRepository userRepository;

    instead of:
    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    e.g.:
    @Mock
    List<String> mockedList;

    instead of:
    List mockList = Mockito.mock(ArrayList.class);

    e.g.: Mocking Interfaces with default methods
    interface AnInterface {
        default boolean isTrue() {
            return true;
        }
    }

    AnInterface mock = mock(AnInterface.class);
    assertFalse(mock.isTrue());

@InjectMocks: creates an instance of the class (don't used for interface) and injects the mocks that are created with
the @Mock annotations into this instance. Test with dependency injection:

    @InjectMocks is necessary for injecting both @Spy and @Mock instances.

    e.g.: @InjectMocks to inject the mocks Object B and C into Object A.
    Object B (@Mock)		Object C (@Mock)
                    \       /
                Object A (@InjectMocks)

@Spy: Spy on real object, on an existing instance. Delegates all method calls to the real object and records what
method was called and with what parameters.

    e.g.:
    @Spy
    List<String> spiedList = new ArrayList<String>();

    instead of
    List<String> spyList = Mockito.spy(new ArrayList<String>());

@Rule

@Test: define a test method

@DisplayName: define the test name displayed to the user.

@RepeatedTest: defines that the test method will be executed multiple times.

@Ignore: ignore the test case.

@Captor
    ArgumentCaptor: capture method arguments

	e.g.:
	@Captor
    private ArgumentCaptor<Post> postArgumentCaptor;
    	postArgumentCaptor: capture the arguments which are passed to the save() method and verify whether the object passed is according to our requirements or not
	
	postService.save(postRequest);
	
	Mockito.verify(postRepository, Mockito.times(1)).save(postArgumentCaptor.capture());

- Stubbing and Exceptions:

from package org.mockito, mockito-core-1.10.19, Mockito.class:
    mock
    spy
    stub
    when
    verify: make sure the method is called with these arguments. Verify method inside a Mock.
    reset
    doThrow
    doAnswer
    doNothing
    doReturn
    times
    never
    atLeast
    atMost
    timeout

from package org.mockito.stubbing, OngoingStubbing.class:
    thenReturn
    thenThrow
    thenCallRealMethod
    thenAnswer
    then

e.g.:
    // when, thenReturn
    when({expression}).thenReturn({result}); // when this method is called, then do something
        e.g.:
        when(customerServiceImpl.searchParticipant(eq(participantBO), eq(true))).thenReturn(null)
        when(passwordEncoder.encode("1")).thenReturn("a");

    doReturn({result}).when({expression}); // do something when this mock’s method is called with the following arguments
        e.g.:
        doReturn("a").when(passwordEncoder).encode("1");

- Argument Matchers (Matchers.java):
package org.mockito
https://www.javadoc.io/doc/org.mockito/mockito-core/2.7.7/org/mockito/ArgumentMatchers.html

eq
    e.g.:
    eq(String.class)

any
    e.g.:
    any(HttpEntity.class)

anyBoolean
anyByte
anyChar
anyInt
anyLong
anyFloat
anyDouble
anyShort
anyObject
anyString
anyList
anyListOf
anySet
anyMap
anyMapOf
    e.g.:
    anyMapOf(String.class, String.class)

anyCollection
same
isNull
notNull
isNotNull
contains
endsWith
startsWith

e.g.:
    1:
    // when the password encoder is asked to encode any string, then return the string 'exact.'
    when(passwordEncoder.encode(anyString())).thenReturn("exact");
    assertEquals("exact", passwordEncoder.encode("1"));
    assertEquals("exact", passwordEncoder.encode("abc"));

    // for many arguments, use "eq":
    when(mock.call(eq("a"), anyInt())).thenReturn(true);

    2:
    @Mock
    private PCTDR001 pctdR001;

    when(pctdR001.executeGetBussinessId(
    eq(DOCUMENT_TYPE),
    same(entityIn),
    .thenReturn(cardProposal);

ref:
Getting Started with Mockito @Mock, @Spy, @Captor and @InjectMocks
https://www.baeldung.com/mockito-annotations

*************************************************

* Throwing Exceptions:

- passing an instance of an exception (Mockito):

thenThrow() and doThrow() are the same

when({expression}).thenThrow({exception});

    // thenThrow
    when(passwordEncoder.encode("1")).thenThrow(new IllegalArgumentException());

    when(ppcuR001.executeCreateCardProposal(eq(DOCUMENT_TYPE)))
    .thenThrow(new BusinessException(BusinessError.BAD_CARD_DELIVERY_DESTINATION.getCodeAdvice(), false));

    // doThrow
    doThrow(new IllegalArgumentException()).when(passwordEncoder).encode("1");

- passing an exception's class (Mockito):

    // thenThrow
    when(passwordEncoder.encode("1")).thenThrow(IllegalArgumentException.class);

    Mockito.when(externalApiConnector.exchange(eq(Constantes.STR_LIST_BRANCHS), eq(HttpMethod.POST), Mockito.anyObject(), Mockito.eq(String.class))).thenThrow(RestClientException.class);

    when(internalApiConnector.getForEntity(eq(Constantes.STR_GET_UBICACION), eq(ResponseGeographicPlacesDTO.class),
    anyMap())).thenThrow(RestClientException.class);

    // doThrow
    doThrow(IllegalArgumentException.class).when(passwordEncoder).encode("1");

- with AssertJ (JUnit 5 built-in Assertions):

    assertThatThrownBy(() -> {
        commentService.containsSwearWords("This is a shitty comment");
    }).isInstanceOf(SpringRedditException.class)
        .hasMessage("Comments contains unacceptable language");

    instead of:

    SpringRedditException exception = assertThrows(SpringRedditException.class, () -> {
        commentService.containsSwearWords("This is shitty comment");
    });
    assertTrue(exception.getMessage().contains("Comments contains unacceptable language"));

- test for exception using ExpectedException (JUnit 4):

e.g.:
    import org.junit.Rule;
    import org.junit.Test;
    import org.junit.rules.ExpectedException;

    public class MyTestClass {

        @Rule
        public ExpectedException expectedException = ExpectedException.none();

        @Test
        public void testExpectedException() {
            expectedException.expect(NumberFormatException.class);
            expectedException.expectMessage("For input string: \"One\"");
            Integer.parseInt("One");
        }

        @Test
        public void testExpectedExceptionWithParentType() {
            expectedException.expect(IllegalArgumentException.class);
            Integer.parseInt("One");
        }
    }

e.g.:
    import org.junit.Rule;
    import org.junit.Test;
    import org.junit.rules.ExpectedException;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void executeGetAapByChannelTestError(){
        expectedException.expect(BusinessException.class);
        when(applicationConfigurationService.getProperty("ppgs.getAap-by-channelCode.CM")).thenReturn(null);
        String aap=PPGSR041.executeGetAapByChannel("CM");
        assertEquals("13000076",aap);
    }

    e.g.:
    import org.junit.Rule;
    import org.junit.Test;
    import org.junit.rules.ExpectedException;
    import org.springframework.batch.item.validator.ValidationException;

     @Rule
     public ExpectedException expectedException = ExpectedException.none();

     @Test
     public void validate_exception(){
         UserDTO user = new UserDTO();
         user.setUserId("");
         this.expectedException.expect(ValidationException.class); // can be any exception class
         this.userValidator.validate(user); // method to test
     }
     // ref: batch_risk_users, RiskUserValidatorTest.java

- test for exception defining exception class in @Test (JUnit 4):
@Test(expected={SomeException.class})

For better control use:
    ExpectedException

e.g.:
     @Test(expected=BusinessException.class)
     public void executeGetAapNoConfiguredConsole() {
         when(applicationConfigurationService.getProperty(anyString())).thenReturn(null);
         phiar041.executeGetAapByChannel("");
     }

e.g.:
     @Test(expected = NullPointerException.class)
         public void whenMockitoAnnotationsUninitialized_thenNPEThrown() {
         Mockito.when(mockedList.size()).thenReturn(1);
     }

ref:
JUnit 5 Expected Exception – assertThrows() Example
https://howtodoinjava.com/junit5/expected-exception-example/#:~:text=In%20JUnit%205%2C%20to%20write,type%20ApplicationException%20or%20its%20subtype.&text=Note%20that%20in%20JUnit%204,%40Test(expected%20%3D%20NullPointerException.

JUnit Assert Exception - JUnit 5 and JUnit 4
https://www.digitalocean.com/community/tutorials/junit-assert-exception-expected

*************************************************

* Test for void method (Mockito):

e.g.:
    verify(ppcuR001).executeCreateEventProposalCreate(any(CreateProposalEvent.class), anyString());

    verify(pctdR001, never()).executeGetBussinessId(eq(AAP));

e.g.: Verifying Behaviour with Mockito
    PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    when(passwordEncoder.encode("a")).thenReturn("1");
    passwordEncoder.encode("a");
    verify(passwordEncoder).encode("a");

refs:
https://davidvlijmincx.com/posts/mock_void_methods_with_mockito/#:~:text=To%20make%20a%20void%20method,the%20mocked%20method%20is%20called.&text=SomeClass%20mockInstance%20%3D%20mock(SomeClass.,class)%3B%20Mockito.
https://www.baeldung.com/mockito-void-methods

*************************************************

* Disable test:

@Disable // JUnit 5

@Ignore // Mockito

*************************************************

* Enable use of annotations with Mockito:

- using MockitoJUnitRunner:

@ExtendWith(MockitoExtension.class)
public class MockitoAnnotationUnitTest {
...
}

- using MockitoAnnotations.openMocks():

@BeforeEach
public void init() {
    MockitoAnnotations.openMocks(this);
}

- using MockitoJUnit.rule():

public class MockitoAnnotationsInitWithMockitoJUnitRuleUnitTest {
    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();
    ...
}

*************************************************

* References

- @SuppressWarnings
suppress warning, from JDK.
    @SuppressWarnings("static-access")

- Unit Testing examples:
https://github.com/eugenp/tutorials/tree/master/core-java-modules/core-java-streams-2/src/test

- @Mock vs @InjectMocks
https://www.geeksforgeeks.org/difference-between-mock-and-injectmocks-in-mockito/

- Mock vs Spy in Mockito
https://www.baeldung.com/mockito-spy

- @Resource vs @Mock:
@Resource
    Resource.class, package javax.annotation
    for dependency injection in Java EE applications.
    to inject resources such as JNDI (Java Naming and Directory Interface) resources or
    other managed resources into a class.
    not recommended used for testing (for isolation and control over dependencies).

    e.g.: dataSource is injected into MyClass using @Resource.
    public class MyClass {
        @Resource
        private DataSource dataSource;

        // Other methods...
    }

    e.g.:
    @Resource(name = "phiaR001")
    private PHIAR001 phiaR001;

@Mock
    for creating mock objects in unit tests with Mockito.

*************************************************/

/*
 Sample for JUnit v5.8.2 and Mockito v4.5.1:

service -> model -> data
*/

//@ExtendWith(MockitoExtension.class) // enable use Mockito annotation in this class
public class UserServiceTest {

    @InjectMocks
    // inject mocks (UsersRepository, EmailVerificationServiceImpl) automatically to the class for testing (UserServiceImpl),
    // create a new instance of its implementation
    UserServiceImpl userService;

    @Mock // for simulate
    UsersRepository usersRepository;

    @Mock // for simulate
    EmailVerificationServiceImpl emailVerificationService;

    @Spy
    List<String> spiedList = new ArrayList<String>();

    @Mock
    List<String> mockedList;

    String firstName;
    String lastName;
    String email;
    String password;
    String repeatPassword;

    /*
    Initialization
     */
    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
        // initialize mockito annotations
        MockitoAnnotations.openMocks(this);
        //MockitoAnnotations.initMocks(this); // deprecated

        //userService = new UserServiceImpl(usersRepository, emailVerificationService); // optional

        // initialize fields
        firstName = "Sergey";
        lastName  = "Kargopolov";
        email = "test@test.com";
        password = "12345678";
        repeatPassword = "12345678";
    }

    /*
    Test a method that returns an object.
     */
    @DisplayName("User object created")
    @Test
    void createUser_whenUserDetailsProvided_returnsUserObject() {
        // Arrange
        Mockito.when(usersRepository.save(any(User.class))).thenReturn(true); // mock for return value

        // Act
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);

        // Assert
        assertNotNull(user, "The createUser() should not have returned null");
        assertNotNull(user.getId(), "User id is missing");
        assertEquals(firstName, user.getFirstName(), "User's first name is incorrect.");
        assertEquals(lastName, user.getLastName(), "User's last name is incorrect");
        assertEquals(email, user.getEmail(), "User's email is incorrect");

        // verify if the method inside of mock (usersRepository) is invoked
        Mockito.verify(usersRepository).save(any(User.class));

        // verify if the mock (usersRepository) is invoked 1 time
        //Mockito.verify(usersRepository, Mockito.times(1));

        //Mockito.atLeast();
        //Mockito.atMost();
        //Mockito.never();
    }

    /*
    Test a method that returns an object with defined inputs (matchers).
     */
    @DisplayName("User object created")
    @Test
    void setMessage_whenUserDetailsDefined_returnsNothing() {

        // Arrange
//        Message msg = mock(Message.class);
        Mockito.when(usersRepository.printMessage(eq("message 1"), any(User.class))).thenReturn(new Message()); // mock for return value
//        Mockito.when(usersRepository.printMessage(eq("message 1"), any(User.class))).thenReturn(msg); // mock for return value

        User user1 = new User("1","firstName", "lastName", "email");

        // Act
        userService.setMessage("message 1", user1);

        // verify if the method inside of mock (usersRepository) is invoked
        Mockito.verify(usersRepository).printMessage(anyString(), any(User.class));
    }

    /*
    Test a method that returns an exception and assert the message.
     */
    @DisplayName("Empty first name causes correct exception")
    @Test
    void createUser_whenFirstNameIsEmpty_throwsIllegalArgumentException() {
        // Arrange
        String firstName = ""; // to provoke the exception
        String expectedExceptionMessage = "User's first name is empty";

        // Act & Assert
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            userService.createUser(firstName, lastName, email, password, repeatPassword);
        },"Empty first name should have caused an Illegal Argument Exception");

        // Assert
        assertEquals(expectedExceptionMessage, thrown.getMessage(),
                "Exception error message is not correct");
    }

    @DisplayName("Empty last name causes correct exception")
    @Test
    void createUser_whenLastNameIsEmpty_throwsIllegalArgumentException() {
        // Arrange
        String lastName = ""; // to provoke the exception
        String expectedExceptionMessage = "User's last name is empty";

        // Act & Assert
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            userService.createUser(firstName, lastName, email, password, repeatPassword);
        },"Empty last name should have caused an Illegal Argument Exception");

        // Assert
        assertEquals(expectedExceptionMessage,thrown.getMessage(),
                "Exception error message is not correct");
    }

    /*
    Test a method that contains a mock that returns an exception.
     */
    @DisplayName("If save() method causes RuntimeException, a UserServiceException is thrown")
    @Test
    void createUser_whenSaveMethodThrowsException_throwsUserServiceException() {
        // Arrange
        when(usersRepository.save(any(User.class))).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(UserServiceException.class, ()-> {
            userService.createUser(firstName, lastName, email, password, repeatPassword);
        }, "Should have thrown UserServiceException instead");
    }

    /*
    Test a void method (mock).
     */
    @DisplayName("Schedule Email Confirmation is executed")
    @Test
    void createUser_whenUserCreated_schedulesEmailConfirmation() {
        // Arrange
        when(usersRepository.save(any(User.class))).thenReturn(true); // for mock

        // invoke the void method (optional)
//        doCallRealMethod().when(emailVerificationService)
//                .scheduleEmailConfirmation(any(User.class));

        // Act
        userService.createUser(firstName, lastName, email, password, repeatPassword);

        // Assert
        verify(emailVerificationService).scheduleEmailConfirmation(any(User.class));
//        verify(emailVerificationService, times(1))
//                .scheduleEmailConfirmation(any(User.class)); // verify if the method is executed 1 time
    }

    @Test
    void whenAddCalled_thenVerified() {
        MyList myList = mock(MyList.class);
        doNothing().when(myList).add(isA(Integer.class), isA(String.class));
        myList.add(0, "");

        verify(myList, times(1)).add(0, "");
    }

    // same as whenAddCalled_thenVerified()
    @Test
    void whenAddCalled_thenVerified2() {
        MyList myList = mock(MyList.class);
        myList.add(0, "");

        verify(myList, times(1)).add(0, "");
    }

    /*
    Test a void method (mock) that throws exception.
     */
    @Test
    @DisplayName("EmailNotificationServiceException is handled")
    void createUser_whenEmailVerificationService_throwsEmailNotificationServiceException() {
        // Arrange
        when(usersRepository.save(any(User.class))).thenReturn(true); // for mock

        // stub/test for void method is different
//        when(emailVerificationService.scheduleEmailConfirmation(any(User.class)))
//                .thenThrow(); // incorrect
        doThrow(EmailNotificationServiceException.class)
                .when(emailVerificationService)
                .scheduleEmailConfirmation(any(User.class));

        // set a test do nothing
//        doNothing().when(emailVerificationService)
//               .scheduleEmailConfirmation(any(User.class));

        // Act & Assert
        assertThrows(EmailNotificationServiceException.class, ()-> {
            userService.createUser(firstName, lastName, email, password, repeatPassword);
        }, "Should have thrown UserServiceException instead");

        // Assert
        verify(emailVerificationService, times(1)).
                scheduleEmailConfirmation(any(User.class));
    }

    @Test
    void givenNull_whenAddCalled_thenThrowsException() {
        MyList myList = mock(MyList.class);

        assertThrows(Exception.class, () -> {
            doThrow().when(myList).add(isA(Integer.class), isNull());
        });

        myList.add(0, null);
    }

    /**************************************************/

    /* Other tests */

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    /*
    Using @Spy
     */
    @Test
    void whenUseSpyAnnotation_thenSpyIsInjectedCorrectly() {
        // Used the real method spiedList.add() to add elements to the spiedList.
        // Stubbed the method spiedList.size() to return 100 instead of 2 using Mockito.doReturn().

        spiedList.add("one");
        spiedList.add("two");

        Mockito.verify(spiedList).add("one");
        Mockito.verify(spiedList).add("two");

        assertEquals(2, spiedList.size());

        Mockito.doReturn(100).when(spiedList).size(); // stubbing a Spy
        assertEquals(100, spiedList.size());
    }

    /*
    Use fail()
     */
    // used when a test is incomplete.
    @Test
    void fail_whenIncompleteTest() {
        fail("Not yet implemented");
    }

    // used when an exception will happen.
    @Test
    public void fail_whenExpectedException() {
        try {
            when(usersRepository.save(any(User.class))).thenThrow(RuntimeException.class);
            userService.createUser(firstName, lastName, email, password, repeatPassword); // methodThrowsException()
            fail("Expected exception was not thrown");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    // used when an exception is not expected to be thrown.
    @Test
    public void fail_whenUnexpectedException() {
        try {
            Mockito.when(usersRepository.save(any(User.class))).thenReturn(true);
            User user = userService.createUser(firstName, lastName, email, password, repeatPassword); // safeMethod
            // more testing code
        } catch (Exception e) {
            fail("Unexpected exception was thrown");
        }
    }

    // used when a result doesn't meet some desired condition.
    @Test
    public void fail_whenTestingCondition() {
        int result = new Random().nextInt(100); // random integer
        if(result > Integer.MAX_VALUE) {
            fail("Result cannot exceed integer max value");
        }
        // more testing code
    }

    // used when the code doesn’t return/break when expected.
    @Test
    public void returnBefore() {
        int value = new Random().nextInt(100); // random integer
        for (int i = 0; i < 5; i++) {
            // returns when (value + i) is an even number
            if ((i + value) % 2 == 0) {
                return;
            }
        }
        fail("Should have returned before");
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }
}

class MyList extends AbstractList<String> {

    @Override
    public String get(int index) {
        return null;
    }

    @Override
    public void add(int index, String element) {
        // no-op
    }

    @Override
    public int size() {
        return 0;
    }
}
