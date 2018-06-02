import os
import re
from sys import argv

def replaceFileContent(src,dest,file):
    f = open(file,'r')
    filedata = f.read()
    f.close()

    newdata = filedata.replace(src,dest)

    f = open(file,'w')
    f.write(newdata)
    f.close()
def multi_replace(search, replace, path):
    """Replace search with replace in all filenames
    and file contents in directory path.
    @type   search: string
    @param  search: The old string.
    @type   replace: string
    @param  replace: The new string.
    @type   path: string
    @param  path: The path in which files area.
    @rtype: boolean
    @returns: True or False. Also print a msg to the console.
    """
    counter_contents = 0
    counter_names = 0
    replacedFiles=[]
    if not os.path.exists(path):
        print 'Path does not exist'
        return False
    for dirpath, dirs, files in os.walk(path):
        for filename in files:
            realPath=os.path.join(dirpath, filename)
            if realPath.find('.git') >= 0 :
                continue
            if realPath.find('.idea') >= 0:
                continue
            if realPath.find('.iml') >= 0:
                continue
            if realPath.find('initProject.py') >= 0:
                continue
            # replace contents
            indata = open(os.path.join(dirpath, filename)).read()
            if search in indata:
                new = indata.replace(search, replace)
                output = open(os.path.join(dirpath, filename), "w")
                output.write(new)
                counter_contents += 1
                replacedFiles.append(realPath)

            # replace in filename
            if search in filename:
                os.rename(
                    os.path.join(dirpath, filename),
                    os.path.join(dirpath, filename.replace(search, replace))
                )
                counter_names += 1

    print '%s files renamed, %s files contents altered' % (counter_names, counter_contents)
    print  replacedFiles
    return True
def rename_package(search, replace, path):
    """Replace search with replace in all filenames
    and file contents in directory path.
    @type   search: string
    @param  search: The old string.
    @type   replace: string
    @param  replace: The new string.
    @type   path: string
    @param  path: The path in which files area.
    @rtype: boolean
    @returns: True or False. Also print a msg to the console.
    """
    counter_contents = 0
    counter_names = 0
    replacedFiles=[]
    if not os.path.exists(path):
        print 'Path does not exist'
        return False
    for dirpath, dirs, files in os.walk(path):
        for filename in files:
            realPath=os.path.join(dirpath, filename)
            if realPath.find('.git') >= 0 :
                continue
            if realPath.find('.idea') >= 0:
                continue
            if realPath.find('.iml') >= 0:
                continue
            if realPath.find('initProject.py') >= 0:
                continue
            # replace contents
            indata = open(os.path.join(dirpath, filename)).read()
            if search in indata:
                new = indata.replace(search, replace)
                output = open(os.path.join(dirpath, filename), "w")
                output.write(new)
                counter_contents += 1
                replacedFiles.append(realPath)


    print ' %s files contents altered %s=>%s' % ( counter_contents,search,replace)
    return True

def move_to_package(search, replace, path):
    srcFolder='/'.join(search.split('.'))
    destFolder='/'.join(replace.split('.'))
    """Replace search with replace in all filenames
    and file contents in directory path.
    @type   search: string
    @param  search: The old string.
    @type   replace: string
    @param  replace: The new string.
    @type   path: string
    @param  path: The path in which files area.
    @rtype: boolean
    @returns: True or False. Also print a msg to the console.
    """
    counter_contents = 0
    counter_names = 0
    replacedFiles=[]
    if not os.path.exists(path):
        print 'Path does not exist'
        return False
    for dirpath, dirs, files in os.walk(path):
        for filename in files:
            realPath=os.path.join(dirpath, filename)
            if realPath.find('.git') >= 0 :
                continue
            if realPath.find('.idea') >= 0:
                continue
            if realPath.find('.iml') >= 0:
                continue
            if realPath.find('initProject.py') >= 0:
                continue
            # replace in filename
            fullpath=os.path.join(dirpath, filename)
            if srcFolder in fullpath:
                targetFile=fullpath.replace(srcFolder, destFolder)
                targetParent=os.path.abspath(os.path.join(targetFile, os.pardir))
                if not os.path.exists(targetParent):
                    os.makedirs(targetParent)
                os.rename(
                    fullpath,
                    targetFile
                )
                counter_names += 1

    print '%s files renamed %s=>%s' % (counter_names,srcFolder,destFolder)
    return True
def rename_folder(src,dest,path):
    temp = os.listdir(path)
    for file in temp:
        if file.find(src) != -1:
            f = file.replace(src, dest)
            if os.path.exists(f):
                os.remove(f)
            print 'rename ' + file + " =>" + f
            os.rename(file, f)



# f = []
# for (root, dirnames, filenames) in os.walk("."):
#     for name in filenames:
#         f.append(os.path.join(root, name))

# for file in f:

name = 'test-proj'
targetPkg='test.proj'
template = 'vue-web-mvc'
templateStr='vue.web.mvc'
if len(argv) > 1:
    projectName = argv[1]
    targetPkg = argv[2]
else:
    projectName=raw_input('Input Project Name: ')
    targetPkg=raw_input('Input Project Package: ')
name=projectName
rename_folder(template, name, '.')
multi_replace(template, name, '.')
rename_package(templateStr,targetPkg,'.')
move_to_package(templateStr,targetPkg,'.')