If we declare global access for that instance ?(global variable)
But this should not be modified, what if some application module using this instance modifies this instance
Hence cannot declare it as global variable

EAGER LOADING: The instance is already initialized as soon as the application is up.
LAZY LOADING :The instance is initialized only when any App Module calls for it.
Save memory using lazy loading 
Singleton never accepts parameters

  
