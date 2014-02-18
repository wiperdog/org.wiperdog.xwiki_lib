
def scriptFile = getClass().protectionDomain.codeSource.location.path
def scriptDir = new File(scriptFile).parent

def mongod = '/home/ora112d/mongodb/bin/mongod'

def pidfilepath = scriptDir + '/run/mongod.pid'
def logpath =  scriptDir + '/log/mongod.log'
def dbpath = scriptDir + '/data'
def fork = " --fork " // use on linux
def otherargs = Args.serverargs(args)

(new File(pidfilepath)).parentFile.exists() ? true : (new File(pidfilepath)).parentFile.mkdirs()
(new File(logpath)).parentFile.exists() ? true : (new File(logpath)).parentFile.mkdirs()
(new File(dbpath)).exists() ? true : (new File(dbpath)).mkdirs()

pidfilepath = " --pidfilepath " + pidfilepath
logpath = " --logpath " + logpath
dbpath = " --dbpath " + dbpath

def startupcmd = ""
if (System.getProperty("os.name").contains('Win')) {
	startupcmd = mongod + pidfilepath  + dbpath + logpath + otherargs
} else {
	startupcmd = mongod + pidfilepath  + dbpath + logpath + fork + otherargs
}
println startupcmd

def proc = startupcmd.execute()
proc.waitFor()

println proc.text
