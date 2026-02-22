# KitMerger Project

## Overview
`KitMergerProject` is a Java command-line utility built to assist with bulk bank card production workflows. Its primary objective is to ingest an unsorted batch of card production records and a corresponding list of newly generated Kit Numbers, match them via their Primary Account Number (PAN), and dynamically update the card record with its respective Kit Number. 

This processed output file allows the card manufacturing systems to systematically pair the physically printed cards with the correct collateral (welcome letters and PIN mailers) specified by the assigned Kit Number.

## Technical Details
This application was refactored based on the logic of the `SortedCard.java` processor.

*   **Inputs:**
    1.  **Kit File (`pc_kit_number_to_be_issued_*.txt`)**: A comma-separated file containing the `PAN` and `Kit Number` on each line, with no header.
    2.  **Card File (`pc_cards_number_to_be_issued_*.txt`)**: A dense 75-column comma-separated file detailing the full manufacturing specs for each card. This file contains a header and `<Trailer>` line.
*   **Core Logic (`KitMerger.java`)**:
    *   Loads both the input Kit file and Card file into respective `ArrayList<Kit>` and `ArrayList<Card>` structures in memory.
    *   Iterates through them to find matching PANs.
    *   Replaces the 7th field (`cardholderName` at field index 6) of the matching **Card** record with the **Kit Number**.
    *   Re-constructs the 75-column output exactly mimicking the input file format, preserving unmatched cards and header/trailer rows.
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
Run the compiled `KitMerger` class by providing the exact sequence of arguments:

```bash
java KitMerger <path_to_kit_file.txt> <path_to_card_file.txt> <path_to_output_file.txt>
```

#### Example Usage
```bash
java KitMerger pc_kit_number_to_be_issued_test.txt pc_cards_number_to_be_issued_test.txt output_test.txt
```
