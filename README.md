# Calculate_Metagenomic_Diversity
A simple Java-based GUI (python backend) to calculate Alpha and Beta diversity on post-[centrifuge](https://github.com/DaehwanKimLab/centrifuge "Link to Centrifuge Github page") 'abundance' files.

## Appearance
![Screenshot] (https://github.com/DrATedder/Calculate_Metagenomic_Diversity/blob/f87d242fad28d21e74ddf3bcc0dae584b6ed3fa2/App_image.png "Calculate_Metagenomic_Diversity App appearance")

## Features
- **User-Friendly Interface:** The app offers a simple and intuitive GUI for selecting your data and performing the analysis.

- **Browse for Files:** Easily select the directory that contains your input files by using the "Browse" button.

- **Text Box:** Understand which parts of the analysis have been completed with helpful text outputs.

## Usage requirements

Before using this application, ensure you have the following installed:

- Java Runtime Environment (JRE)
- Python (with required dependencies for the Python script)

**Note.** The python backend of this app requires the following modules: `pandas`, `skbio` & `scipy`. These can be installed using `pip` or via anaconda depending on how python is installed on your system. For more information on installing python modules, please see [here](https://docs.python.org/3/installing/index.html "Link to Python Docs guide to installing modules").

## Usage

1. Clone or download this repository to your local machine.

2. Compile the Java code:

   ```bash
   javac Calculate_Metagenomic_Diversity.java

3. Run the Java application:

    ```bash

    java Calculate_Metagenomic_Diversity

4. Use the app to browse for the input directory whioch contains the appropriate 'abundance' files, and click the "Run Analysis" button to start the calculation process.

**Requirements:**

- directory containing sample files (*abundance.txt format; see below for naming protocols). For more about 'abundance' files, see [here](https://github.com/DrATedder/ancient_metagenomics "Link to further details about generating 'abundance' files."). Alternatively, these can be generated using the ['centrifuge_2_abundance'](https://github.com/DrATedder/centrifuge_2_abundance "Link to centrifuge_2_abundance java app github page") java app.

**File naming protocol:** Abundance files should be named in the following manner:

> shortname_anything_abundance.txt

1.    shortname: used to name samples in diversity outputs.
2.    anything: not used, but can be anything
3.    abundance.txt: used by the programme to identify the correct files within the given directory
4.    underscores ('_') must be used between file name elements as these are used for splitting file names

**Note.** Abundance files are 'csv' format (albeit with a 'txt' extension), and have the following format, where column 1 is *OTU name*, column 2 is *read count* and column 3 is *abundnance*:
|     |     |     |
| --- | --- | --- |
|Azorhizobium caulinodans | 1725 | 0.03|
|Cellulomonas gilvus | 2019 | 0.03|
|Myxococcus xanthus | 5519 | 0.08|
|Myxococcus macrosporus | 4463 | 0.07|
|Stigmatella aurantiaca | 1622 | 0.02|
|Cystobacter fuscus | 2504 | 0.04|
|Archangium gephyra | 3011 | 0.04|
|Chondromyces crocatus | 1719 | 0.03|
|Sorangium cellulosum | 16403 | 0.24|
|Vitreoscilla filiformis | 1746 | 0.03|

### Output files ###

Calculate_Metagenomic_Diversity produces three output files:
1. Alpha divesity ('alpha_diversity_results.csv')
2. Beta diversity matrix ('beta_diversity_matrix.csv')
3. Count matrix ('count_matrix.csv')

To integrate this process into a bash/python pipeline, use the python backend 'alpha_beta_diversity.py'. Full details for this can be found [here](https://github.com/DrATedder/ancient_metagenomics "Link to ancient_metagenomics github page.").

## Author

    Dr. Andrew Tedder
    University of Bradford

## License

This project is licensed under the MIT License - see the LICENSE file for details.
|Lysobacter enzymogenes | 44962 | 0.66|
|Stella humosa | 2887 | 0.04| 
