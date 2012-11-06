/*
 * CKFinder
 * ========
 * http://ckfinder.com
 * Copyright (C) 2007-2012, CKSource - Frederico Knabben. All rights reserved.
 *
 * The software, this file, and its contents are subject to the CKFinder
 * License. Please read the license.txt file before using, installing, copying,
 * modifying, or distributing this file or part of its contents. The contents of
 * this file is part of the Source Code of CKFinder.
 *
 */

/**
 * @fileOverview Defines the {@link CKFinder.lang} object for the English
 *		language. This is the base file for all translations.
 */

/**
 * Contains the dictionary of language entries.
 * @namespace
 */
CKFinder.lang['ko'] =
{
	appTitle : 'CKFinder',

	// Common messages and labels.
	common :
	{
		// Put the voice-only part of the label in the span.
		unavailable		: '%1<span class="cke_accessibility">, 사용불가</span>',
		confirmCancel	: '옵션이 변경되었습니다. 창을 닫으시겠습니까?',
		ok				: '확인',
		cancel			: '취소',
		confirmationTitle	: '확인',
		messageTitle	: '정보',
		inputTitle		: '질문',
		undo			: '취소',
		redo			: '재실행',
		skip			: 'Skip',
		skipAll			: 'Skip all',
		makeDecision	: '어떤 동작을 하시겠습니까?',
		rememberDecision: '내 결정을 기억'
	},


	// Language direction, 'ltr' or 'rtl'.
	dir : 'ltr',
	HelpLang : 'en',
	LangCode : 'ko',

	// Date Format
	//		d    : Day
	//		dd   : Day (padding zero)
	//		m    : Month
	//		mm   : Month (padding zero)
	//		yy   : Year (two digits)
	//		yyyy : Year (four digits)
	//		h    : Hour (12 hour clock)
	//		hh   : Hour (12 hour clock, padding zero)
	//		H    : Hour (24 hour clock)
	//		HH   : Hour (24 hour clock, padding zero)
	//		M    : Minute
	//		MM   : Minute (padding zero)
	//		a    : Firt char of AM/PM
	//		aa   : AM/PM
	DateTime : 'yyyy년 m월 d일 H:MM',
	DateAmPm : ['AM','PM'],

	// Folders
	FoldersTitle	: '폴더',
	FolderLoading	: '로딩중...',
	FolderNew		: '새 폴더 이름을 입력해주세요: ',
	FolderRename	: '새 폴더 이름을 입력해주세요: ',
	FolderDelete	: '"%1" 폴더를 삭제하시겠습니까?',
	FolderRenaming	: ' (이름 변경중...)',
	FolderDeleting	: ' (삭제중...)',

	// Files
	FileRename		: '새 파일명을 입력해주세요: ',
	FileRenameExt	: '확장자를 변경하시겠습니까? 파일을 사용할 수 없게 될 수 있습니다.',
	FileRenaming	: '이름 변경중...',
	FileDelete		: '"%1"를 삭제하시겠습니까?',
	FilesLoading	: '로딩중...',
	FilesEmpty		: '폴더가 비어있습니다.',
	FilesMoved		: '%1 파일이 %2:%3로 이동되었습니다.',
	FilesCopied		: '%1 파일이 %2:%3로 복사되었습니다.',

	// Basket
	BasketFolder		: 'Basket',
	BasketClear			: 'Clear Basket',
	BasketRemove		: 'Remove from Basket',
	BasketOpenFolder	: 'Open Parent Folder',
	BasketTruncateConfirm : 'Do you really want to remove all files from the basket?',
	BasketRemoveConfirm	: 'Do you really want to remove the file "%1" from the basket?',
	BasketEmpty			: 'No files in the basket, drag and drop some.',
	BasketCopyFilesHere	: 'Copy Files from Basket',
	BasketMoveFilesHere	: 'Move Files from Basket',

	BasketPasteErrorOther	: 'File %s error: %e',
	BasketPasteMoveSuccess	: 'The following files were moved: %s',
	BasketPasteCopySuccess	: 'The following files were copied: %s',

	// Toolbar Buttons (some used elsewhere)
	Upload		: '업로드',
	UploadTip	: '새 파일 업로드',
	Refresh		: '새로고침',
	Settings	: '세팅',
	Help		: '도움말&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;한글화: Mr. 하늘 (심영재)&nbsp;&nbsp;&nbsp;http://mr.hanul.co&nbsp;&nbsp;&nbsp;mr@hanul.co&nbsp;',
	HelpTip		: '도움말',

	// Context Menus
	Select			: '선택',
	SelectThumbnail : '썸네일 선택',
	View			: '보기',
	Download		: '다운로드',

	NewSubFolder	: '새 폴더',
	Rename			: '파일명 변경',
	Delete			: '삭제',

	CopyDragDrop	: '여기에 파일 복사',
	MoveDragDrop	: '여기에 파일 이동',

	// Dialogs
	RenameDlgTitle		: '이름 바꾸기',
	NewNameDlgTitle		: '새 이름',
	FileExistsDlgTitle	: '이미 존재하는 파일',
	SysErrorDlgTitle : '시스템 오류',

	FileOverwrite	: '덮어쓰기',
	FileAutorename	: '자동 이름 바꾸기',

	// Generic
	OkBtn		: '확인',
	CancelBtn	: '취소',
	CloseBtn	: '닫기',

	// Upload Panel
	UploadTitle			: '새 파일 업로드',
	UploadSelectLbl		: '업로드할 파일을 선택하세요.',
	UploadProgressLbl	: '(업로드중, 잠시 기다려주세요...)',
	UploadBtn			: '선택한 파일을 업로드',
	UploadBtnCancel		: '취소',

	UploadNoFileMsg		: '파일을 선택해 주세요.',
	UploadNoFolder		: '폴더를 선택해 주세요.',
	UploadNoPerms		: '파일 업로드가 불가능합니다.',
	UploadUnknError		: '파일 전송중 오류가 발생했습니다.',
	UploadExtIncorrect	: '이 폴더에 업로드 불가능한 확장자입니다.',

	// Flash Uploads
	UploadLabel			: '업로드할 파일',
	UploadTotalFiles	: '전체 파일수:',
	UploadTotalSize		: '전체 파일 크기:',
	UploadSend			: '업로드',
	UploadAddFiles		: '파일 추가',
	UploadClearFiles	: '초기화',
	UploadCancel		: '업로드 취소',
	UploadRemove		: '삭제',
	UploadRemoveTip		: '!f를 삭제',
	UploadUploaded		: '!n% 업로드되었습니다.',
	UploadProcessing	: '진행중...',

	// Settings Panel
	SetTitle		: '세팅',
	SetView			: '보기:',
	SetViewThumb	: '썸네일',
	SetViewList		: '목록',
	SetDisplay		: '표시:',
	SetDisplayName	: '파일명',
	SetDisplayDate	: '날짜',
	SetDisplaySize	: '파일 크기',
	SetSort			: '정렬:',
	SetSortName		: '이름순',
	SetSortDate		: '최신순',
	SetSortSize		: '파일 크기순',
	SetSortExtension		: '확장자순',

	// Status Bar
	FilesCountEmpty : '<빈 폴더>',
	FilesCountOne	: '1개의 파일',
	FilesCountMany	: '%1개의 파일',

	// Size and Speed
	Kb				: '%1 kB',
	KbPerSecond		: '%1 kB/s',

	// Connector Error Messages.
	ErrorUnknown	: 'It was not possible to complete the request. (Error %1)',
	Errors :
	{
	 10 : 'Invalid command.',
	 11 : 'The resource type was not specified in the request.',
	 12 : 'The requested resource type is not valid.',
	102 : 'Invalid file or folder name.',
	103 : 'It was not possible to complete the request due to authorization restrictions.',
	104 : 'It was not possible to complete the request due to file system permission restrictions.',
	105 : 'Invalid file extension.',
	109 : '잘못된 요청입니다.',
	110 : 'Unknown error.',
	115 : 'A file or folder with the same name already exists.',
	116 : 'Folder not found. Please refresh and try again.',
	117 : 'File not found. Please refresh the files list and try again.',
	118 : 'Source and target paths are equal.',
	201 : 'A file with the same name is already available. The uploaded file was renamed to "%1".',
	202 : 'Invalid file.',
	203 : 'Invalid file. The file size is too big.',
	204 : 'The uploaded file is corrupt.',
	205 : 'No temporary folder is available for upload in the server.',
	206 : 'Upload cancelled due to security reasons. The file contains HTML-like data.',
	207 : 'The uploaded file was renamed to "%1".',
	300 : 'Moving file(s) failed.',
	301 : 'Copying file(s) failed.',
	500 : 'The file browser is disabled for security reasons. Please contact your system administrator and check the CKFinder configuration file.',
	501 : 'The thumbnails support is disabled.'
	},

	// Other Error Messages.
	ErrorMsg :
	{
		FileEmpty		: 'The file name cannot be empty.',
		FileExists		: 'File %s already exists.',
		FolderEmpty		: 'The folder name cannot be empty.',

		FileInvChar		: 'The file name cannot contain any of the following characters: \n\\ / : * ? " < > |',
		FolderInvChar	: 'The folder name cannot contain any of the following characters: \n\\ / : * ? " < > |',

		PopupBlockView	: 'It was not possible to open the file in a new window. Please configure your browser and disable all popup blockers for this site.',
		XmlError		: 'It was not possible to properly load the XML response from the web server.',
		XmlEmpty		: 'It was not possible to load the XML response from the web server. The server returned an empty response.',
		XmlRawResponse	: 'Raw response from the server: %s'
	},

	// Imageresize plugin
	Imageresize :
	{
		dialogTitle		: '%s 크기 변경',
		sizeTooBig		: '원래 크기보다 큰 값으로 이미지 크기를 변경할 수 없습니다. (%size).',
		resizeSuccess	: '이미지 크기가 변경되었습니다.',
		thumbnailNew	: '새 썸네일 생성',
		thumbnailSmall	: '작게 (%s)',
		thumbnailMedium	: '중간 (%s)',
		thumbnailLarge	: '크게 (%s)',
		newSize			: '새 크기를 입력해주세요.',
		width			: '넓이',
		height			: '높이',
		invalidHeight	: '잘못된 높이입니다.',
		invalidWidth	: '잘못된 넓이입니다.',
		invalidName		: '잘못된 파일명입니다.',
		newImage		: '새 이미지 생성',
		noExtensionChange : '파일 확장자는 변경할 수 없습니다.',
		imageSmall		: '원본 이미지가 너무 작습니다.',
		contextMenuName	: '크기 변경',
		lockRatio		: '비율 고정',
		resetSize		: '크기 초기화'
	},

	// Fileeditor plugin
	Fileeditor :
	{
		save			: '저장',
		fileOpenError	: '파일을 열지 못했습니다.',
		fileSaveSuccess	: '파일이 저장되었습니다.',
		contextMenuName	: '편집',
		loadingFile		: '파일을 불러오는 중입니다, 잠시만 기다려주세요...'
	},

	Maximize :
	{
		maximize : '최대화',
		minimize : '최소화'
	}
};
