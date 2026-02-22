//package Serge.Card.File.Reader;

public class Card {
    public String pan;
    public String sequenceNumber;
    public String fromDate;
    public String fromDay;
    public String expiryDate;
    public String expiryDay;
    public String cardholderName;
    public String cardholderTitle;
    public String cardholderFirstName;
    public String cardholderInitials;
    public String cardholderLastName;
    public String customerID;
    public String issuerReference;
    public String companyName;
    public String cardType;
    public String cvv;
    public String cvv2;
    public String generateSecurePIN;
    public String securePINOffset;
    public String securePINBlock;
    public String generateInsecurePIN;
    public String insecurePINOffset;
    public String insecurePINBlock;
    public String produceCard;
    public String produceCardMailer;
    public String producePINMailer;
    public String track2Data;
    public String cardMailerAddress1;
    public String cardMailerAddress2;
    public String cardMailerAddressCity;
    public String cardMailerAddressRegion;
    public String cardMailerAddressPostalCode;
    public String pinMailerAddress1;
    public String pinMailerAddress2;
    public String pinMailerAddressCity;
    public String pinMailerAddressRegion;
    public String pinMailerAddressPostalCode;
    public String cardCustomState;
    public String securePINLength;
    public String insecurePINLength;
    public String cardholderInfo;
    public String branchCode;
    public String dateIssued;
    public String batchNumber;
    public String nationalID;
    public String telephoneNr;
    public String mobileNr;
    public String fax;
    public String emailAddress;
    public String address1;
    public String address2;
    public String city;
    public String region;
    public String postalCode;
    public String country;
    public String dateOfBirth;
    public String preferredLang;
    public String extendedCard;
    public String extendedCustomer;
    public String defaultAccountType;
    public String account;
    public String accountType;
    public String accountProduct;
    public String extendedAccount;
    public String iCVV;
    public String iCVV2;
    public String csc3;
    public String scs4;
    public String csc5;
    public String icsc3;
    public String icsc4;
    public String icsc5;
    public String iCSC5EMVContact;
    public String iCSC5EMVContactless;
    public String iCSC5FallbackMagstripe;

    public Card(String pan, String sequenceNumber, String fromDate, String fromDay, String expiryDate, String expiryDay,
            String cardholderName, String cardholderTitle, String cardholderFirstName, String cardholderInitials,
            String cardholderLastName, String customerID, String issuerReference, String companyName,
            String cardType, String cvv, String cvv2, String generateSecurePIN, String securePINOffset,
            String securePINBlock, String generateInsecurePIN, String insecurePINOffset, String insecurePINBlock,
            String produceCard, String produceCardMailer, String producePINMailer, String track2Data,
            String cardMailerAddress1, String cardMailerAddress2, String cardMailerAddressCity,
            String cardMailerAddressRegion, String cardMailerAddressPostalCode, String pinMailerAddress1,
            String pinMailerAddress2, String pinMailerAddressCity, String pinMailerAddressRegion,
            String pinMailerAddressPostalCode, String cardCustomState, String securePINLength,
            String insecurePINLength, String cardholderInfo, String branchCode, String dateIssued, String batchNumber,
            String nationalID, String telephoneNr, String mobileNr, String fax, String emailAddress, String address1,
            String address2, String city, String region, String postalCode, String country, String dateOfBirth,
            String preferredLang, String extendedCard, String extendedCustomer, String defaultAccountType,
            String account, String accountType, String accountProduct, String extendedAccount, String iCVV,
            String iCVV2, String csc3, String scs4, String csc5, String icsc3, String icsc4, String icsc5,
            String iCSC5EMVContact, String iCSC5EMVContactless, String iCSC5FallbackMagstripe) {
        this.pan = pan;
        this.sequenceNumber = sequenceNumber;
        this.fromDate = fromDate;
        this.fromDay = fromDay;
        this.expiryDate = expiryDate;
        this.expiryDay = expiryDay;
        this.cardholderName = cardholderName;
        this.cardholderTitle = cardholderTitle;
        this.cardholderFirstName = cardholderFirstName;
        this.cardholderInitials = cardholderInitials;
        this.cardholderLastName = cardholderLastName;
        this.customerID = customerID;
        this.issuerReference = issuerReference;
        this.companyName = companyName;
        this.cardType = cardType;
        this.cvv = cvv;
        this.cvv2 = cvv2;
        this.generateSecurePIN = generateSecurePIN;
        this.securePINOffset = securePINOffset;
        this.securePINBlock = securePINBlock;
        this.generateInsecurePIN = generateInsecurePIN;
        this.insecurePINOffset = insecurePINOffset;
        this.insecurePINBlock = insecurePINBlock;
        this.produceCard = produceCard;
        this.produceCardMailer = produceCardMailer;
        this.producePINMailer = producePINMailer;
        this.track2Data = track2Data;
        this.cardMailerAddress1 = cardMailerAddress1;
        this.cardMailerAddress2 = cardMailerAddress2;
        this.cardMailerAddressCity = cardMailerAddressCity;
        this.cardMailerAddressRegion = cardMailerAddressRegion;
        this.cardMailerAddressPostalCode = cardMailerAddressPostalCode;
        this.pinMailerAddress1 = pinMailerAddress1;
        this.pinMailerAddress2 = pinMailerAddress2;
        this.pinMailerAddressCity = pinMailerAddressCity;
        this.pinMailerAddressRegion = pinMailerAddressRegion;
        this.pinMailerAddressPostalCode = pinMailerAddressPostalCode;
        this.cardCustomState = cardCustomState;
        this.securePINLength = securePINLength;
        this.insecurePINLength = insecurePINLength;
        this.cardholderInfo = cardholderInfo;
        this.branchCode = branchCode;
        this.dateIssued = dateIssued;
        this.batchNumber = batchNumber;
        this.nationalID = nationalID;
        this.telephoneNr = telephoneNr;
        this.mobileNr = mobileNr;
        this.fax = fax;
        this.emailAddress = emailAddress;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.preferredLang = preferredLang;
        this.extendedCard = extendedCard;
        this.extendedCustomer = extendedCustomer;
        this.defaultAccountType = defaultAccountType;
        this.account = account;
        this.accountType = accountType;
        this.accountProduct = accountProduct;
        this.extendedAccount = extendedAccount;
        this.iCVV = iCVV;
        this.iCVV2 = iCVV2;
        this.csc3 = csc3;
        this.scs4 = scs4;
        this.csc5 = csc5;
        this.icsc3 = icsc3;
        this.icsc4 = icsc4;
        this.icsc5 = icsc5;
        this.iCSC5EMVContact = iCSC5EMVContact;
        this.iCSC5EMVContactless = iCSC5EMVContactless;
        this.iCSC5FallbackMagstripe = iCSC5FallbackMagstripe;
    }
}
