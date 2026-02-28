# KitMerger Project

## Overview
`KitMergerProject` is a Java command-line utility built to assist with bulk bank card production workflows. Its primary objective is to ingest an unsorted batch of card production records and a corresponding list of newly generated Kit Numbers, match them via their Primary Account Number (PAN), and dynamically update the card record with its respective Kit Number. 

This processed output file allows the card manufacturing systems to systematically pair the physically printed cards with the correct collateral (welcome letters and PIN mailers) specified by the assigned Kit Number.

## Technical Details
This application was refactored based on the logic of the `SortedCard.java` processor.

*   **Inputs:**
    *   **Source Folder**: The program scans this directory for files starting with `pc_kit_number_to_be_issued_` and `pc_cards_to_be_issued_`.
*   **Outputs:**
    *   **Output Folder**: The processed `output.txt` is written here.
    *   **Log Folder**: A timestamped log file (`KitMerger_yyyyMMdd_HHmmss.log`) is generated here.
*   **Core Logic (`KitMerger.java`)**:
    *   Loads both the input Kit file and Card file into respective `ArrayList<Kit>` and `ArrayList<Card>` structures in memory.
    *   Iterates through them to find matching PANs.
    *   Replaces the 36th field (`pinMailerAddressRegion` at field index 35) of the matching **Card** record with the **Kit Number**.
    *   Re-constructs the 75-column output exactly mimicking the input file format, preserving unmatched cards and header/trailer rows.
    *   Log instances mask Primary Account Numbers (PAN) matching the 6-asterisk-4 PCI DSS masking requirement.
*   **Data Models**:
    *   `Card.java`: Models the 75 card fields. Alphanumeric fields that could potentially be empty, such as the `PAN` and `issuerReference`, strictly utilize the `String` type natively to avoid parsing errors.
    *   `Kit.java`: A minimal model housing `pan` and `kitNumber`.

## Execution Guide

### Prerequisites
*   Ensure the Java Development Kit (JDK) is installed and available in your environment path.

### Compilation
If changes are made to the `.java` files, you must compile them. Navigate to `/Users/serge/Documents/LocalDev/KitMergerProject` and run:

```bash
javac Kit.java Card.java KitMerger.java
```

### Running the Utility
Run the compiled JAR file by providing the three arguments (source folder, output folder, and log folder):

```bash
java -jar KitMerger.jar <source_folder> <output_folder> <log_folder>
```

#### Example Usage
```bash
java -jar KitMerger.jar . . logs
```
