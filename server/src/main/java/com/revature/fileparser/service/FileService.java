package com.revature.fileparser.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.io.File;

import com.revature.fileparser.entity.FileMetadata;
import com.revature.fileparser.entity.GenericRecord;
import com.revature.fileparser.entity.SimpleSpecification;
import com.revature.fileparser.repository.FileRepository;
import com.revature.fileparser.repository.SimpleSpecificationRepository;
import com.revature.fileparser.utility.parser.GenericRecordGenerator;
import com.revature.fileparser.utility.parser.Specification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private FileRepository fileRepository;
    private SimpleSpecificationRepository simpleSpecificationRepository;
    private ParsingService parsingService;
    private GenericRecordService genericRecordService;
    private File recordFile;


    @Autowired
    public FileService(FileRepository fileRepository,
                       ParsingService parsingService,
                       GenericRecordService genericRecordService,
                       SimpleSpecificationRepository simpleSpecificationRepository) {
        this.fileRepository = fileRepository;
        this.simpleSpecificationRepository = simpleSpecificationRepository;
        this.parsingService = parsingService;
        this.genericRecordService = genericRecordService;
    }

    public void saveRecordFileMetadata(String uploaderUsername, MultipartFile recordFile) throws IOException {
        String savedDirectory = saveToFileSystem(recordFile, uploaderUsername, 'R');
        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setFileName(recordFile.getOriginalFilename());
        fileMetadata.setSize((int) recordFile.getSize()); // assuming 'size' is in bytes
        fileMetadata.setUploaderUsername(uploaderUsername);
        fileMetadata.setFileLocation(savedDirectory);
        setRecordFile(new File(savedDirectory));
        fileRepository.save(fileMetadata);
    }

    public void saveSimpleSpecification(String uploaderUsername, MultipartFile specFile) throws IOException {
        String savedDirectory = saveToFileSystem(specFile, uploaderUsername, 'S');
        parsingService.setSpec(new Specification(new File((savedDirectory)))); // Set specification for parsing

        SimpleSpecification simpleSpec = new SimpleSpecification(specFile.getOriginalFilename(),
                                                    parsingService.getSpec().getSpecStringJSON(),
                                                    uploaderUsername,
                                                    savedDirectory);
        simpleSpecificationRepository.save(simpleSpec);
    }

    public List<GenericRecord> saveRecordsFromFile(String username, String sourceFileName, String specFileName)
            throws IOException {
        parsingService.setRecordFileName(sourceFileName);
        parsingService.setSpecFileName(specFileName);
        parsingService.setUsername(username);
        parsingService.setGenericRecordGenerator(new GenericRecordGenerator(recordFile, parsingService.getSpec()));
        parsingService.parseIntoGeneralRecords();
        //System.out.println(parsingService.getRecordMapList());
        //parsingService.getGenericRecordGenerator().generateGeneralRecordList();
        return genericRecordService.persistAllRecords(parsingService.getRecordList());
    }

    /**
     * Stores the file locally. Based on the file type, the file is stored in a different directory.
     * @param file
     * @param uploaderUsername
     * @param fileType ('R' for record file or 'S' for specification file)
     * @return file path (in local storage)
     * @throws IOException
     */
    private String saveToFileSystem(MultipartFile file, String uploaderUsername, Character fileType) throws IOException {
        String directoryPath = "D:/WORK/BLOCK STORAGE/" + uploaderUsername;
        if(fileType == 'R') {
            directoryPath += "/flatfile";
        }
        if(fileType == 'S') {
            directoryPath += "/specfile";
        }
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        // Generate a unique filename to prevent conflicts
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        File targetFile = new File(directory, fileName);
        file.transferTo(targetFile);
        return directoryPath + "/" + fileName;
    }

    public void setRecordFile(File recordFile) {
        this.recordFile = recordFile;
    }

    public List<FileMetadata> getFilesByUsername(String username){
        return fileRepository.findByUploaderUsername(username);
    }

    public List<SimpleSpecification> getSpecsByUsername(String username){
        return simpleSpecificationRepository.findByUploaderUsername(username);
    }

}
