import java.util.ArrayList;
import java.io.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KitMerger {
    private static final Logger LOGGER = Logger.getLogger(KitMerger.class.getName());

    private static String maskPan(String pan) {
        if (pan == null || pan.length() < 10) {
            return "******";
        }
        int len = pan.length();
        StringBuilder masked = new StringBuilder();
        masked.append(pan.substring(0, 6));
        for (int i = 6; i < len - 4; i++) {
            masked.append('*');
        }
        masked.append(pan.substring(len - 4));
        return masked.toString();
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            LOGGER.severe("Usage: java KitMerger <source_folder> <output_folder> <log_folder>");
            System.exit(1);
        }

        String sourceFolder = args[0].replace("\\", File.separator).replace("/", File.separator);
        String outputFolder = args[1].replace("\\", File.separator).replace("/", File.separator);
        String logFolder = args[2].replace("\\", File.separator).replace("/", File.separator);

        File logDir = new File(logFolder);
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String logFileName = new File(logDir, "KitMerger_" + timeStamp + ".log").getAbsolutePath();
            FileHandler fileHandler = new FileHandler(logFileName);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            LOGGER.severe("Failed to initialize logger file handler: " + e.getMessage());
        }

        File sourceDir = new File(sourceFolder);
        if (!sourceDir.isDirectory()) {
            LOGGER.severe("Source folder is not a directory: " + sourceFolder);
            System.exit(1);
        }

        File[] kitFiles = sourceDir.listFiles(
                (dir, name) -> name.startsWith("pc_kit_number_to_be_issued_") && new File(dir, name).isFile());
        if (kitFiles == null || kitFiles.length == 0) {
            LOGGER.severe("Kit file with prefix 'pc_kit_number_to_be_issued_' not found in source folder.");
            System.exit(1);
        }
        String kitFile = kitFiles[0].getAbsolutePath();

        // Looking for prefix pc_cards_to_be_issued_ (and pc_cards_number_to_be_issued_
        // for backwards compatibility in tests)
        File[] cardFiles = sourceDir.listFiles((dir,
                name) -> (name.startsWith("pc_cards_to_be_issued_") || name.startsWith("pc_cards_number_to_be_issued_"))
                        && new File(dir, name).isFile());
        if (cardFiles == null || cardFiles.length == 0) {
            LOGGER.severe("Card file with prefix 'pc_cards_to_be_issued_' not found in source folder.");
            System.exit(1);
        }
        String cardFile = cardFiles[0].getAbsolutePath();

        File outDir = new File(outputFolder);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        String outputFile = new File(outDir, "output.txt").getAbsolutePath();

        ArrayList<Kit> kitRecords = new ArrayList<>();
        ArrayList<Card> cardRecords = new ArrayList<>();

        // 1. Read Kit File
        LOGGER.info("Reading Kit file: " + kitFile);
        try (BufferedReader kitReader = new BufferedReader(new FileReader(kitFile))) {
            String currentLine;

            while ((currentLine = kitReader.readLine()) != null) {
                currentLine = currentLine.trim();
                if (currentLine.isEmpty())
                    continue;

                String[] splitLine = currentLine.split(",");
                if (splitLine.length >= 2) {
                    String pan = splitLine[0].trim();
                    String kitNumber = splitLine[1].trim();
                    LOGGER.info("Loaded Kit record with PAN: " + maskPan(pan) + ", Kit Number: " + kitNumber);
                    kitRecords.add(new Kit(pan, kitNumber));
                }
            }
            LOGGER.info("Loaded " + kitRecords.size() + " kit records.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. Read Card File
        LOGGER.info("Reading Card file: " + cardFile);
        String header = "";
        String footer = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(cardFile))) {
            header = reader.readLine();

            String currentLine = "";
            while ((currentLine = reader.readLine()) != null) {
                currentLine = currentLine.trim();

                if (currentLine.isEmpty()) {
                    continue;
                }
                if (currentLine.startsWith("<Trailer>")) {
                    footer = currentLine + "\r\n";
                    break;
                }

                String[] splitLine = currentLine.split(",");
                String[] cardDetail;
                if (splitLine.length < 75) {
                    cardDetail = new String[75];
                    System.arraycopy(splitLine, 0, cardDetail, 0, splitLine.length);
                    for (int i = splitLine.length; i < 75; i++) {
                        cardDetail[i] = "";
                    }
                } else {
                    cardDetail = splitLine;
                }

                String pan = cardDetail[0];
                String sequenceNumber = cardDetail[1];
                String fromDate = cardDetail[2];
                String fromDay = cardDetail[3];
                String expiryDate = cardDetail[4];
                String expiryDay = cardDetail[5];
                String cardholderName = cardDetail[6];
                String cardholderTitle = cardDetail[7];
                String cardholderFirstName = cardDetail[8];
                String cardholderInitials = cardDetail[9];
                String cardholderLastName = cardDetail[10];
                String customerID = cardDetail[11];
                String issuerReference = cardDetail[12];
                String companyName = cardDetail[13];
                String cardType = cardDetail[14];
                String cvv = cardDetail[15];
                String cvv2 = cardDetail[16];
                String generateSecurePIN = cardDetail[17];
                String securePINOffset = cardDetail[18];
                String securePINBlock = cardDetail[19];
                String generateInsecurePIN = cardDetail[20];
                String insecurePINOffset = cardDetail[21];
                String insecurePINBlock = cardDetail[22];
                String produceCard = cardDetail[23];
                String produceCardMailer = cardDetail[24];
                String producePINMailer = cardDetail[25];
                String track2Data = cardDetail[26];
                String cardMailerAddress1 = cardDetail[27];
                String cardMailerAddress2 = cardDetail[28];
                String cardMailerAddressCity = cardDetail[29];
                String cardMailerAddressRegion = cardDetail[30];
                String cardMailerAddressPostalCode = cardDetail[31];
                String pinMailerAddress1 = cardDetail[32];
                String pinMailerAddress2 = cardDetail[33];
                String pinMailerAddressCity = cardDetail[34];
                String pinMailerAddressRegion = cardDetail[35];
                String pinMailerAddressPostalCode = cardDetail[36];
                String cardCustomState = cardDetail[37];
                String securePINLength = cardDetail[38];
                String insecurePINLength = cardDetail[39];
                String cardholderInfo = cardDetail[40];
                String branchCode = cardDetail[41];
                String dateIssued = cardDetail[42];
                String batchNumber = cardDetail[43];
                String nationalID = cardDetail[44];
                String telephoneNr = cardDetail[45];
                String mobileNr = cardDetail[46];
                String fax = cardDetail[47];
                String emailAddress = cardDetail[48];
                String address1 = cardDetail[49];
                String address2 = cardDetail[50];
                String city = cardDetail[51];
                String region = cardDetail[52];
                String postalCode = cardDetail[53];
                String country = cardDetail[54];
                String dateOfBirth = cardDetail[55];
                String preferredLang = cardDetail[56];
                String extendedCard = cardDetail[57];
                String extendedCustomer = cardDetail[58];
                String defaultAccountType = cardDetail[59];
                String account = cardDetail[60];
                String accountType = cardDetail[61];
                String accountProduct = cardDetail[62];
                String extendedAccount = cardDetail[63];
                String iCVV = cardDetail[64];
                String iCVV2 = cardDetail[65];
                String csc3 = cardDetail[66];
                String scs4 = cardDetail[67];
                String csc5 = cardDetail[68];
                String icsc3 = cardDetail[69];
                String icsc4 = cardDetail[70];
                String icsc5 = cardDetail[71];
                String iCSC5EMVContact = cardDetail[72];
                String iCSC5EMVContactless = cardDetail[73];
                String iCSC5FallbackMagstripe = cardDetail[74];

                cardRecords.add(new Card(pan, sequenceNumber, fromDate, fromDay, expiryDate, expiryDay, cardholderName,
                        cardholderTitle, cardholderFirstName, cardholderInitials, cardholderLastName, customerID,
                        issuerReference, companyName, cardType, cvv, cvv2, generateSecurePIN, securePINOffset,
                        securePINBlock, generateInsecurePIN, insecurePINOffset, insecurePINBlock, produceCard,
                        produceCardMailer, producePINMailer, track2Data, cardMailerAddress1, cardMailerAddress2,
                        cardMailerAddressCity, cardMailerAddressRegion, cardMailerAddressPostalCode, pinMailerAddress1,
                        pinMailerAddress2, pinMailerAddressCity, pinMailerAddressRegion, pinMailerAddressPostalCode,
                        cardCustomState, securePINLength, insecurePINLength, cardholderInfo, branchCode, dateIssued,
                        batchNumber, nationalID, telephoneNr, mobileNr, fax, emailAddress, address1, address2, city,
                        region, postalCode, country, dateOfBirth, preferredLang, extendedCard, extendedCustomer,
                        defaultAccountType, account, accountType, accountProduct, extendedAccount, iCVV, iCVV2, csc3,
                        scs4, csc5, icsc3, icsc4, icsc5, iCSC5EMVContact, iCSC5EMVContactless, iCSC5FallbackMagstripe));
                LOGGER.info("Read Card record with PAN: " + maskPan(pan));
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading file", e);
        }

        // 3. Process - merge kit numbers into matched cards
        LOGGER.info("Merging records...");
        int matchedCount = 0;
        for (Card card : cardRecords) {
            for (Kit kit : kitRecords) {
                if (card.pan.equals(kit.pan)) {
                    LOGGER.info("Match found for PAN: " + maskPan(card.pan) + " with Kit: " + kit.kitNumber);
                    card.cardholderName = kit.kitNumber;
                    matchedCount++;
                    break;
                }
            }
        }
        LOGGER.info("Matched " + matchedCount + " records.");

        // 4. Write Output
        LOGGER.info("Writing output file: " + outputFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            if (header != null) {
                writer.write(header);
                writer.newLine();
            }
            for (Card card : cardRecords) {
                writer.write(card.pan + "," + card.sequenceNumber + "," + card.fromDate + "," + card.fromDay
                        + "," + card.expiryDate + "," + card.expiryDay + "," + card.cardholderName + ","
                        + card.cardholderTitle
                        + "," + card.cardholderFirstName + "," + card.cardholderInitials + "," + card.cardholderLastName
                        + "," + card.customerID + "," + card.issuerReference + "," + card.companyName + ","
                        + card.cardType
                        + "," + card.cvv + "," + card.cvv2 + "," + card.generateSecurePIN + "," + card.securePINOffset
                        + "," + card.securePINBlock + "," + card.generateInsecurePIN + "," + card.insecurePINOffset
                        + "," + card.insecurePINBlock + "," + card.produceCard + "," + card.produceCardMailer
                        + "," + card.producePINMailer + "," + card.track2Data + "," + card.cardMailerAddress1
                        + "," + card.cardMailerAddress2 + "," + card.cardMailerAddressCity + ","
                        + card.cardMailerAddressRegion
                        + "," + card.cardMailerAddressPostalCode + "," + card.pinMailerAddress1 + ","
                        + card.pinMailerAddress2
                        + "," + card.pinMailerAddressCity + "," + card.pinMailerAddressRegion + ","
                        + card.pinMailerAddressPostalCode
                        + "," + card.cardCustomState + "," + card.securePINLength + "," + card.insecurePINLength + ","
                        + card.cardholderInfo
                        + "," + card.branchCode + "," + card.dateIssued + "," + card.batchNumber + "," + card.nationalID
                        + "," + card.telephoneNr + "," + card.mobileNr + "," + card.fax + "," + card.emailAddress
                        + "," + card.address1 + "," + card.address2 + "," + card.city + "," + card.region + ","
                        + card.postalCode
                        + "," + card.country + "," + card.dateOfBirth + "," + card.preferredLang + ","
                        + card.extendedCard
                        + "," + card.extendedCustomer + "," + card.defaultAccountType + "," + card.account + ","
                        + card.accountType
                        + "," + card.accountProduct + "," + card.extendedAccount + "," + card.iCVV + "," + card.iCVV2
                        + "," + card.csc3 + "," + card.scs4 + "," + card.csc5 + "," + card.icsc3 + "," + card.icsc4
                        + "," + card.icsc5 + "," + card.iCSC5EMVContact + "," + card.iCSC5EMVContactless + ","
                        + card.iCSC5FallbackMagstripe);
                writer.newLine();
            }
            if (footer != null && !footer.isEmpty()) {
                writer.write(footer);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing output file", e);
        }
        LOGGER.info("Done.");
    }
}
